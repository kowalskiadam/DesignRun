package pl.kowalskiadam.designrun.app.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayRepository extends JpaRepository<Day, Long>{

    List<Day> findByWeekId(Long id);


}
