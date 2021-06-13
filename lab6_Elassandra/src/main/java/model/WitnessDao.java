package model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Witness;

@Dao
public interface WitnessDao {
    @Select
    Witness findById(Integer id);

    @Insert
    void save(Witness witness);

    @Update
    void update(Witness witness);

    @Delete
    void delete(Witness witness);
}
