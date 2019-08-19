package pl.kowalskiadam.designrun.app.user;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

   @Autowired
   UserRepository userRepository;

   public UniqueUserValidator() {
   }

   public void initialize(UniqueUser constraint) {
   }

   public boolean isValid(String login, ConstraintValidatorContext context) {
      User user = userRepository.findByLogin(login);
      try {
         if (user.getId() > 0){
            return false;
         }
      } catch (NullPointerException e){
         return true;
      }
      return true;
   }
}
