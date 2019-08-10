package pl.kowalskiadam.designrun.app.method;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {
    
    @Query("select t from TrainingType t where t.method.id = :id")
    List<TrainingType> getByMethodId(@Param("id") Long id);
}
