package wd.pledge.guarantee.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import wd.pledge.guarantee.util.LogicalState;
import wd.pledge.guarantee.util.PhysicalState;

@Entity
@Table(name = "pledge")
public class Pledge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pledgeId;

    @Size(max = 256)
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Float value;

//    @Column(name = "warehouse_in_time")
//    private Time warehouseInTime;

    @Column(name = "logical_state")
    @Enumerated(EnumType.STRING)
    private LogicalState logicalState;

    @Column(name = "physical_state")
    @Enumerated(EnumType.STRING)
    private PhysicalState physicalState;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Location location;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "record_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Record record;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "device_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Device device;

    public Pledge(){}

    public int getPledgeId() {
        return pledgeId;
    }

    public void setPledgeId(int pledgeId) {
        this.pledgeId = pledgeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

//    public Time getWarehouseInTime() {
//        return warehouseInTime;
//    }
//
//    public void setWarehouseInTime(Time warehouseInTime) {
//        this.warehouseInTime = warehouseInTime;
//    }

    public LogicalState getLogicalState() {
        return logicalState;
    }

    public void setLogicalState(LogicalState logicalState) {
        this.logicalState = logicalState;
    }

    public PhysicalState getPhysicalState() {
        return physicalState;
    }

    public void setPhysicalState(PhysicalState physicalState) {
        this.physicalState = physicalState;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
