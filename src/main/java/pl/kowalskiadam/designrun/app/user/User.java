package pl.kowalskiadam.designrun.app.user;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@MappedSuperclass
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Size(min = 5, max = 30)
        @Column(unique = true, length = 30)
        private String login;

        public Long getId() {
                return id;
        }

        public String getLogin() {
                return login;
        }

        public void setLogin(String login) {
                this.login = login;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", login='" + login + '\'' +
                        '}';
        }
}
