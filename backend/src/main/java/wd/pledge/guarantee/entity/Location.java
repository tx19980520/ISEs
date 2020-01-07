package wd.pledge.guarantee.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "location")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Size(max = 256)
    @Column(name = "description")
    private String description;

    @Column(name = "is_used")
    private boolean isUsed;

    /*
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pledge_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Pledge pledge;
    */

    public Location(){}

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    /*public Pledge getPledge() {
        return pledge;
    }

    public void setPledge(Pledge pledge) {
        this.pledge = pledge;
    }*/
}
