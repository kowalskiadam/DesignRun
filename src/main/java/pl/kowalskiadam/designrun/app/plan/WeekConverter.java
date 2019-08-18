package pl.kowalskiadam.designrun.app.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.Convert;
import java.lang.annotation.Annotation;

public class WeekConverter implements Converter<String , Week> {

    @Autowired
    WeekRepository weekRepository;

    @Override
    public Week convert(String s){
        Long id = Long.parseLong(s);
        return weekRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
