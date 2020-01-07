package wd.pledge.guarantee.service;

import wd.pledge.guarantee.util.PhysicalState;

public interface DeviceService {
    boolean changePledgeStatus(Integer deviceId, Integer pledgeId, PhysicalState physicalState);
}
