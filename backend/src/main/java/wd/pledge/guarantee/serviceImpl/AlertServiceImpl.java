package wd.pledge.guarantee.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import wd.pledge.guarantee.dto.AlertInfo;
import wd.pledge.guarantee.dto.DeviceMessage;
import wd.pledge.guarantee.dto.PhysicalMessage;
import wd.pledge.guarantee.entity.Device;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.repository.DeviceRepository;
import wd.pledge.guarantee.service.AlertService;
import wd.pledge.guarantee.service.PledgeService;
import wd.pledge.guarantee.service.RedisService;
import wd.pledge.guarantee.util.AlertType;
import wd.pledge.guarantee.util.PhysicalState;

import javax.validation.constraints.Null;

@Service
public class AlertServiceImpl implements AlertService {

  @Autowired
  private RedisService redisService;
  @Autowired
  private DeviceRepository deviceRepository;
  private Lock lock;
  private Condition alertCondition;
  private float temp;
  private float humidity;
  private List<JSONObject> imageList;

  @Autowired
  private PledgeService pledgeService;

  @Autowired
  public void lock() {
    this.lock = new ReentrantLock();
    this.alertCondition = lock.newCondition();
  }


  @Autowired
  public void imageList() {
    this.imageList = new ArrayList<>();
  }

  public List<JSONObject> getImageList() {
    return imageList;
  }

  public float getTemp() {
    return temp;
  }

  public float getHumidity() {
    return humidity;
  }

  @Override
  public SseEmitter send(String name) throws IOException {
    SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
    sseMvcExecutor.execute(() -> {
      try {
        // Device device = deviceRepository.findByTopic(name);
        // check
        // find by value
        System.out.println("I'm after lock");
        do {
          lock.lock();
          System.out.println("I'm after await");
          SseEventBuilder event = SseEmitter.event()
              .data(new AlertInfo("thing id", AlertType.SHAKE))
              .name(name)
              .reconnectTime(1000L);
          emitter.send(event);
          alertCondition.await();
        } while (true);
      } catch (Exception ex) {
        System.out.println(ex.toString());
        System.out.println(ex.getMessage());
        emitter.completeWithError(ex);
      }
    });
    return emitter;
  }

  @Override
  public void receiveHandler(MessageToken messageToken) {
    lock.lock();

    Message message = messageToken.getMessage();

    System.out.println("Find where is the pic1: Just get message");
    System.out.println(new String(message.getPayload()));
    String topic = message.getTopic();


    // picture into list<js>
    if (topic.contains("picture")) {
      System.out.println("get picture");
      String imgInfo = new String(message.getPayload());
      System.out.println("ImageInfo:" + imgInfo);
      JSONObject jsonObject = JSONObject.parseObject(imgInfo);
      System.out.println("JSONOBJECT:" + jsonObject.toJSONString());
      if (imageList.size() == 6) {
        imageList.remove(0);
        imageList.add(jsonObject);
      }
      else {
        imageList.add(jsonObject);
      }
    }

    if (topic.contains("property")) {
      String info = new String(message.getPayload());
      JSONObject jsonObject = JSONObject.parseObject(info);
      System.out.println("get Property" + jsonObject.toJSONString());
      JSONObject ts = jsonObject.getJSONObject("items");
      System.out.println("get JsonTS" + ts.toJSONString());
      if (ts.containsKey("Temperature")) {
        JSONObject t = ts.getJSONObject("Temperature");
        temp = t.getFloat("value");
      }
      if (ts.containsKey("Humidity")) {
        JSONObject h = ts.getJSONObject("Humidity");
        humidity = h.getFloat("value");
      }
    }

    if (message.getTopic().contains("status")) {
      System.out.println("Find where is the pic2: have status");
      DeviceMessage deviceMessage = new DeviceMessage(new String (message.getPayload()));
      // can update device status
    } else {
      System.out.println("Find where is the pic3: Don't have status");
      PhysicalMessage physicalMessage = new PhysicalMessage(new String (message.getPayload()));
      System.out.println("Don't have status");
      //System.out.println("getAlertType" + physicalMessage.getAlertType());
      Optional<Device> deviceOptional = deviceRepository.findById(physicalMessage.getDeviceName());
      if (deviceOptional.isPresent()) {
        JSONObject jsonObject = JSONObject.parseObject(new String(message.getPayload()));
        String warning = physicalMessage.getItems().getJSONObject("Warning").getString("value");
        Integer id = 0;
        Iterable<Pledge> pledges = pledgeService.findAll();
        for (Pledge pledge: pledges) {
          if (pledge.getDevice().getDeviceId().equals(physicalMessage.getDeviceName())) {
            id = pledge.getPledgeId();
          }
        }
        if (id != 0) {
          switch (warning) {
            case "000":
              pledgeService.setPhysicalState(id, PhysicalState.STABLE);
              pledgeService.setInWarehoused(id);
              break;
            case "100":
              pledgeService.setPhysicalState(id, PhysicalState.SHOCKING);
              break;
            case "110":
            case "111":
              pledgeService.setPhysicalState(id, PhysicalState.MOVING);
              break;
          }
        }
      }

      //redisService.set(message.getTopic(), physicalMessage.getAlertType().getType());
      alertCondition.signalAll();
    }

    lock.unlock();
  }
}
