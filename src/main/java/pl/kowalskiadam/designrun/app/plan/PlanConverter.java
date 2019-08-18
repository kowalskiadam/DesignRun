package pl.kowalskiadam.designrun.app.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PlanConverter implements Converter<String, Plan> {

    @Autowired
    PlanRepository planRepository;

    @Override
    public Plan convert (String s){
        Long id = Long.parseLong(s);
        return planRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
