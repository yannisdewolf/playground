package be.dewolf.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by yannis on 21/01/17.
 */
@Entity
@Audited
@Table(name = "LIGHT")
public class Light {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;




}
