package wd.pledge.guarantee.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import wd.pledge.guarantee.entity.Location;

import javax.transaction.Transactional;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    @Modifying
    @Transactional
    @Query("update Location location set location.isUsed = ?2 where location.locationId = ?1")
    public void setLocationUsed(Integer id, boolean isUsed);

}
