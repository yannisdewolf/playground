package be.dewolf.model;

import be.dewolf.util.DateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yannis on 12/01/17.
 */
@Audited
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "creation_time")
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime localDateTime;

    protected Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.localDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
