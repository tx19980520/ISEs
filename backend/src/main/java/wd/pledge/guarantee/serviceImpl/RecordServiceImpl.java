package wd.pledge.guarantee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.entity.Record;
import wd.pledge.guarantee.repository.PledgeRepository;
import wd.pledge.guarantee.repository.RecordRepository;
import wd.pledge.guarantee.service.RecordService;

import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    PledgeRepository pledgeRepository;

    @Autowired
    RecordRepository recordRepository;

    public Record getRecord(Integer pledgeId)
    {
        Optional<Pledge> pledgeOptional = pledgeRepository.findById(pledgeId);
        if (pledgeOptional.isPresent()) {
            Pledge pledge = pledgeOptional.get();
            Record record = pledge.getRecord();
            return pledge.getRecord();
        }
        return null;
    }
}