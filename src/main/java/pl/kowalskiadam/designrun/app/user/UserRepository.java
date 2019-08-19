package pl.kowalskiadam.designrun.app.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

}
