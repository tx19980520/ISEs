package wd.pledge.guarantee.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wd.pledge.guarantee.entity.Record;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;

@Repository
public interface RecordRepository extends CrudRepository<Record, Integer> {

    @Modifying
    @Transactional
    @Query("update Record record set record.inwarehousedTime = ?2 where record.recordId = ?1")
    public void updateTnwarehousedTime(Integer id, String time);

    @Modifying
    @Transactional
    @Query("update Record record set record.exwarehousingTime = ?2 where record.recordId = ?1")
    public void updateExwarehousingTime(Integer id, String time);

    @Modifying
    @Transactional
    @Query("update Record record set record.exwarehousedTime = ?2 where record.recordId = ?1")
    public void updateExwarehousedTime(Integer id, String time);
}
