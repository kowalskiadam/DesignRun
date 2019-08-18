package pl.kowalskiadam.designrun.app.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Long> {

    List<Week> findByPlanId(Long id);


}
