package model;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;

@Dao
public interface CaseDao {

    @Select
    Case getById(Long id);

    @Insert
    void save(Case aCase);

    @Update
    void update(Case aCase);

    @Delete
    void delete(Case aCase);

    @Select
    PagingIterable<Case> getAll();

    @Select(customWhereClause = "otype = :otype", allowFiltering = true)
    PagingIterable<Case> getByType(String otype);
}
