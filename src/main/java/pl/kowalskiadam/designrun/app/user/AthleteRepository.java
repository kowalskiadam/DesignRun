package pl.kowalskiadam.designrun.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kowalskiadam.designrun.app.method.Method;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

                //select b from Book    b inner join fetch b.categories as c where c.id=:category_id
              //  select c FROM Course c JOIN              c.enrolledStudents u WHERE u.id = :userId

  Athlete findByLogin(String login);


  @Query("select athlete from Athlete athlete inner join fetch athlete.coaches coaches where coaches.id=  ?1")
  List<Athlete> getAthleteByCoaches(Long coachId);

  @Query("select athlete from Athlete athlete inner join fetch athlete.potentialCoaches coaches where coaches.id=  ?1")
  List<Athlete> getPotentialAthleteByCoaches(Long coachId);

}
