package io.imhungryhi.tddmock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor @Getter @Setter
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String street;

    @Column
    private String houseNumber;

    @Column
    private String bus;

    @Column
    private String postal;

    @Column
    private String community;

    @Column
    private String country;
}
