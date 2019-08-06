package pl.kowalskiadam.designrun.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;



public class AthleteConverter implements Converter<String, Athlete> {

    @Autowired
    AthleteRepository athleteRepository;

    @Override
    public Athlete convert(String s){
        Long id = Long.parseLong(s);
        return athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
