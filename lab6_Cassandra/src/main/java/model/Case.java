package model;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Case {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public short getVictims() {
        return victims;
    }

    public void setVictims(short victims) {
        this.victims = victims;
    }

    @PartitionKey
    private Long id;
    private String otype;
    private String status;
    private short victims;
    //private Witness witness;

}
