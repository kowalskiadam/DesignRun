package pl.kowalskiadam.designrun.app.plan;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TrainingFormContainer {

    private List<TrainingForm> trainingForms = new ArrayList<>();

    public List<TrainingForm> getTrainingForms() {
        return trainingForms;
    }

    public void setTrainingForms(List<TrainingForm> trainingForms) {
        this.trainingForms = trainingForms;
    }
}
