package pl.kowalskiadam.designrun.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.kowalskiadam.designrun.app.method.MethodConverter;
import pl.kowalskiadam.designrun.app.method.TrainingTypeConverter;
import pl.kowalskiadam.designrun.app.user.AthleteConverter;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;
import java.util.Locale;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.kowalskiadam.designrun")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages  = "pl.kowalskiadam.designrun")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("bookstorePersistenceUnit");
        return emfb;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean(name="localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pl","PL"));
        return localeResolver; }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getAthleteConverter());
        registry.addConverter(getMethodConverter());
        registry.addConverter(getTrainingTypeConverter());
    }
    @Bean
    public AthleteConverter getAthleteConverter() {
        return new AthleteConverter();
    }

    @Bean
    public MethodConverter getMethodConverter() {
        return new MethodConverter();
    }

    @Bean
    public TrainingTypeConverter getTrainingTypeConverter() {
        return new TrainingTypeConverter();
    }
}
