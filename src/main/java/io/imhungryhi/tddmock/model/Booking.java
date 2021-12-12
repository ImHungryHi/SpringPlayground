package io.imhungryhi.tddmock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor @Getter @Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private Date fromDate;

    @Column
    private Date toDate;

    @Column
    private int numberOfPeople;

    @ManyToOne @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne @JoinColumn(name = "room_id")
    private Room room;
}
