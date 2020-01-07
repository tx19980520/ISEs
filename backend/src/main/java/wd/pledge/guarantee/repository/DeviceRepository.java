package wd.pledge.guarantee.repository;

import org.springframework.data.repository.CrudRepository;
import wd.pledge.guarantee.entity.Device;

public interface DeviceRepository extends CrudRepository<Device, String> {
  Device findByTopic(String topic);
}
