package wd.pledge.guarantee.util;

import org.springframework.lang.Nullable;

public enum AlertType {
  STATE(0),

  MOVE(1),

  SHAKE(2);

  int type;

  AlertType(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }

  @Nullable
  public static AlertType resolve(int t) {
    for (AlertType type : values()) {
      if (type.type == t) {
        return type;
      }
    }
    return null;
  }
}
