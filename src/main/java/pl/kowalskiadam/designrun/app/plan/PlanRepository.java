package pl.kowalskiadam.designrun.app.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("select p from Plan p where p.coach.id = :id")
    List<Plan> getByCoachId(@Param("id") Long id);

    @Query("select p from Plan p where p.athlete.id = :id")
    List<Plan> getByAthleteId(@Param("id") Long id);

}
