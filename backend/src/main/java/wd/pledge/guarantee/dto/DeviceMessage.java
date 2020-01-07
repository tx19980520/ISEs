package wd.pledge.guarantee.dto;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;

public class DeviceMessage implements Serializable {

  public DeviceMessage(String payload) {
    JSONObject j = (JSONObject) JSONObject.parse(payload);
      this.lastTime = j.getString("lastTime");
      this.utcLastTime = j.getString("utcLastTime");
      this.clientIp = j.getString("clientIp");
      this.utcTime = j.getString("utcTime");
      this.productKey = j.getString("productKey");
      this.deviceName = j.getString("deviceName");
      this.status = j.getString("status");
  }

  private String lastTime;
  private String utcLastTime;
  private String clientIp;
  private String utcTime;
  private String productKey;
  private String deviceName;
  private String status;

  public String getLastTime() {
    return lastTime;
  }

  public void setLastTime(String lastTime) {
    this.lastTime = lastTime;
  }

  public String getUtcLastTime() {
    return utcLastTime;
  }

  public void setUtcLastTime(String utcLastTime) {
    this.utcLastTime = utcLastTime;
  }

  public String getClientIp() {
    return clientIp;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }

  public String getUtcTime() {
    return utcTime;
  }

  public void setUtcTime(String utcTime) {
    this.utcTime = utcTime;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
