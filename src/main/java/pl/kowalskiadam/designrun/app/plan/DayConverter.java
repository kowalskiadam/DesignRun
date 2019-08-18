package pl.kowalskiadam.designrun.app.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class DayConverter implements Converter<String, Day> {

    @Autowired
    DayRepository dayRepository;

    @Override
    public Day convert(String s){
        Long id = Long.parseLong(s);
        return dayRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
