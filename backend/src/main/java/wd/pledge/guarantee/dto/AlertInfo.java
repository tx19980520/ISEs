package wd.pledge.guarantee.dto;

import java.io.Serializable;
import wd.pledge.guarantee.util.AlertType;

public class AlertInfo implements Serializable {

  private final String id;

  private final AlertType alertType;

  public AlertInfo(String id, AlertType alertType) {
    this.id = id;
    this.alertType = alertType;
  }

  public AlertType getAlertType() {
    return alertType;
  }

  public String getId() {
    return id;
  }
}
