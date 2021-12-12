package io.imhungryhi.tddmock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor @Getter @Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int numberOfSingleBeds;

    @Column
    private int numberOfDoubleBeds;

    @Column
    private double price;

    @ManyToOne @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
