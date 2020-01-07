package wd.pledge.guarantee.service;

import com.aliyun.openservices.iot.api.message.entity.MessageToken;
import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlertService {


  SseEmitter send(String name) throws IOException;

  void receiveHandler(MessageToken messageToke);


}
