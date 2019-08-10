package pl.kowalskiadam.designrun.app.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.kowalskiadam.designrun.app.plan.TrainingRepository;


public class TrainingTypeConverter implements Converter<String, TrainingType> {

    @Autowired
    TrainingTypeRepository trainingTypeRepository;

    @Override
    public TrainingType convert(String s){
        Long id = Long.parseLong(s);
        return trainingTypeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
