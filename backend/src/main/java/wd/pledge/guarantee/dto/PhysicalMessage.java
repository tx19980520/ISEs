package wd.pledge.guarantee.dto;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import wd.pledge.guarantee.util.AlertType;

public class PhysicalMessage implements Serializable {
  private String deviceType;
  private String iotId;
  private String requestId;
  private String productKey;
  private String deviceName;
  private JSONObject items;

  /*
  * {
  * "deviceType":"CustomCategory",
  * "iotId":"AJcrhU0BZgv4volyOeH4000100",
  * "requestId":"21",
  * "productKey":"a1hGIDupeHM",
  * "gmtCreate":1574776726151,
  * "deviceName":"test_device_1",
  * "items":{"Temperature":{"value":2,"time":1574776726155},
  * "Humidity":{"value":3,"time":1574776726155}}}
  */
  public PhysicalMessage(String payload) {
    JSONObject j = (JSONObject) JSONObject.parse(payload);
    deviceType = j.getString("deviceType");
    iotId = j.getString("iotId");
    requestId = j.getString("requestId");
    productKey = j.getString("productKey");
    deviceName  = j.getString("deviceName");
    items = j.getJSONObject("items");
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getIotId() {
    return iotId;
  }

  public void setIotId(String iotId) {
    this.iotId = iotId;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getProductKey() {
    return productKey;
  }

  public void setProductKey(String productKey) {
    this.productKey = productKey;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public JSONObject getItems() {
    return items;
  }

  public void setItems(JSONObject items) {
    this.items = items;
  }
  public AlertType getAlertType() {
    //return AlertType.resolve(this.items.getJSONObject("shake").getInteger("value"));
    return null;
  }
}
