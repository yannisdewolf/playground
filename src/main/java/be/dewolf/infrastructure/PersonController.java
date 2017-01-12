package be.dewolf.infrastructure;

import be.dewolf.model.Person;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by yannis on 12/01/17.
 */
@RestController
public class PersonController {


    private PersonRepository personRepository;

    private static final Logger LOGGER = Logger.getLogger(PersonController.class);

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ResponseBody
    @RequestMapping(path = "/person/all")
    public List<Person> getPersons() {
        List<Person> all = personRepository.findAll();
        all.forEach(LOGGER::info);
        return all;
    }

    @RequestMapping(path = "/person/create", method = RequestMethod.POST)
    public ResponseEntity<Person> createPerson() {
        Person person = new Person(nextSessionId(), nextSessionId());
        Person save = personRepository.save(person);

        LOGGER.info("saved person " + save);

        return new ResponseEntity<Person>(save, HttpStatus.OK);
    }

    private String nextSessionId() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

}
