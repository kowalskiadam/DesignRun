package pl.kowalskiadam.designrun.app.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.kowalskiadam.designrun.app.method.Method;

import java.util.List;

public interface DayRepository extends JpaRepository<Day, Long>{

    List<Day> findByWeekId(Long id);

    @Query("select d from Day d where d.plan.id = :id order by d.date")
    List<Day> getByPlanId(@Param("id") Long id);


}
