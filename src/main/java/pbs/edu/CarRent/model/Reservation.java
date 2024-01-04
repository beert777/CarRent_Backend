package pbs.edu.CarRent.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="reservation")
@Setter
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private Date startDate;
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private ReservationState state;
    private Date expectedEndDate;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User userReservation;

    @ManyToOne
    @JoinColumn(name="idCar")
    private Car carReservation;

    @ManyToOne
    @JoinColumn(name="idSalonStart")
    private Salon salonStart;
    @ManyToOne
    @JoinColumn(name="idSalonEnd")
    private Salon salonEnd;

    @ManyToOne
    @JoinColumn(name="idEmployeeStart")
    private Employee employeeStart;

    @ManyToOne
    @JoinColumn(name="idEmployeeEnd")
    private Employee employeeEnd;



}
