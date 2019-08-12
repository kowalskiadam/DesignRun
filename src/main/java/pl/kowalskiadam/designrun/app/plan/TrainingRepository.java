package pl.kowalskiadam.designrun.app.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("select t from Training t where t.day.week.plan.id = :id")
    List<Training> getByPlanId(@Param("id") Long id);
    
}
