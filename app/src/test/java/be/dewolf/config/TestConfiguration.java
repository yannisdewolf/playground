package be.dewolf.config;

import be.dewolf.infrastructure.PersonRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yannis on 15/01/17.
 */
@Configuration
public class TestConfiguration {

    public static final String HALLO_VAN_TESTCODE = "Hallo van testcode";

    @Bean
    public PersonRepository personRepository() {
        PersonRepository mock = Mockito.mock(PersonRepository.class);
        Mockito.when(mock.zegHallo()).thenReturn(HALLO_VAN_TESTCODE);
        return mock;
    }

}
