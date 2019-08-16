package pl.kowalskiadam.designrun.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kowalskiadam.designrun.app.method.Method;

import java.util.List;

public interface CoachRepository extends JpaRepository <Coach, Long> {

    Coach findByLogin(String login);

    @Query("select coach from Coach coach inner join fetch coach.athletes athletes where athletes.id=  ?1")
    List<Coach> getCoachesByAthlete(Long coachId);

    @Query("select coach from Coach coach inner join fetch coach.potentialAthletes athletes where athletes.id=  ?1")
    List<Coach> getPotentialCoachesByAthlete(Long coachId);

}
