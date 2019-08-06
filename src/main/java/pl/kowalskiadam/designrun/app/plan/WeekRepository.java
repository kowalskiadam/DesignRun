package pl.kowalskiadam.designrun.app.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Long> {

    List<Week> findByPlanId(Long id);


}
