package pl.kowalskiadam.designrun.app.user;

import org.hibernate.validator.constraints.NotBlank;
import pl.kowalskiadam.designrun.app.secure.Authority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="USER_TYPE",discriminatorType=DiscriminatorType.STRING)
@Table(name = "users")
public abstract class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Size(min = 5, max = 30)
        @Column(unique = true, length = 30)
        //@UniqueUser
        private String login;

        @NotNull
        private String password;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_authority",
                joinColumns = {@JoinColumn(name = "user_id")},
                inverseJoinColumns = {@JoinColumn(name = "authority_id")})

        private Set<Authority> authorities = new HashSet<>();

        public Long getId() {
                return id;
        }

        public String getLogin() {
                return login;
        }

        public void setLogin(String login) {
                this.login = login;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Set<Authority> getAuthorities() {
                return authorities;
        }

        public void setAuthorities(Set<Authority> authorities) {
                this.authorities = authorities;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", login='" + login + '\'' +
                        '}';
        }
}
