package be.dewolf.infrastructure;

import be.dewolf.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yannis on 12/01/17.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
