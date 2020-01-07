package wd.pledge.guarantee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.repository.PledgeRepository;
import wd.pledge.guarantee.service.DeviceService;
import wd.pledge.guarantee.util.LogicalState;
import wd.pledge.guarantee.util.PhysicalState;

import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    PledgeRepository pledgeRepository;

    @Override
    public boolean changePledgeStatus(Integer deviceId, Integer pledgeId, PhysicalState physicalState) {
        Optional<Pledge> pledgeOptional = pledgeRepository.findById(pledgeId);
        if (pledgeOptional.isPresent()) {
            pledgeRepository.updatePledgePhysicalState(pledgeId, physicalState);
            return true;
        }
        else {
            return false;
        }
    }
}
