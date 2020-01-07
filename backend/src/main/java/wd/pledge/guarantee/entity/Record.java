package wd.pledge.guarantee.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @Column(name = "exwarehoused_time")
    private String exwarehousedTime;

    @Column(name = "exwarehousing_time")
    private String exwarehousingTime;

    @Column(name = "inwarehoused_time")
    private String inwarehousedTime;

    @Column(name = "inwarehousing_time")
    private String inwarehousingTime;

    public Record(){}

    public Integer getRecordId() {
        return recordId;
    }

    public String getExwarehousedTime() {
        return exwarehousedTime;
    }

    public String getExwarehousingTime() {
        return exwarehousingTime;
    }

    public String getInwarehousedTime() {
        return inwarehousedTime;
    }

    public String getInwarehousingTime() {
        return inwarehousingTime;
    }

    public void setInwarehousingTime(String inwarehousingTime) {
        this.inwarehousingTime = inwarehousingTime;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public void setInwarehousedTime(String inwarehousedTime) {
        this.inwarehousedTime = inwarehousedTime;
    }

    public void setExwarehousingTime(String exwarehousingTime) {
        this.exwarehousingTime = exwarehousingTime;
    }

    public void setExwarehousedTime(String exwarehousedTime) {
        this.exwarehousedTime = exwarehousedTime;
    }
}
