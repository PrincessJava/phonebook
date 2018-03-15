package su.msk.jet.phonebook.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import su.msk.jet.phonebook.config.AppDataBaseConfig;

import java.nio.charset.StandardCharsets;

@Configuration
@Import({WebConfig.class, AppDataBaseConfig.class})
@ComponentScan({"su.msk.jet.phonebook.service", "su.msk.jet.phonebook.dao", "su.msk.jet.phonebook.mapper"})
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

}
