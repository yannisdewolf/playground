package be.dewolf.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

/**
 * Created by yannis on 14/01/17.
 */
public class PersonTO {

    private long id;
    private String firstName;
    private String lastName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime localDateTime;

    public PersonTO(
            @JsonProperty("id") long id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("localDateTime") LocalDateTime localDateTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.localDateTime = localDateTime;
    }

    public long getId() {
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
        return "PersonTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
