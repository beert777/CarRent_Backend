package pbs.edu.CarRent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name="idSalon")
    private Salon salonEmployee;
}
