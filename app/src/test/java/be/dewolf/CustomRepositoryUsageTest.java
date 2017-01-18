package be.dewolf;

import be.dewolf.config.TestConfiguration;
import be.dewolf.infrastructure.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by yannis on 15/01/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@ContextConfiguration(classes = TestConfiguration.class)
public class CustomRepositoryUsageTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void personRepositoryGebruiktMock() {
        Assert.assertEquals(TestConfiguration.HALLO_VAN_TESTCODE, personRepository.zegHallo());
    }

}
