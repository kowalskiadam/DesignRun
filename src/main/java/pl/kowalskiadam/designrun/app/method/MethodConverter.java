package pl.kowalskiadam.designrun.app.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.kowalskiadam.designrun.app.user.Athlete;
import pl.kowalskiadam.designrun.app.user.AthleteRepository;


public class MethodConverter implements Converter<String, Method> {

    @Autowired
    MethodRepository methodRepository;

    @Override
    public Method convert(String s){
        Long id = Long.parseLong(s);
        return methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
