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
@Table(name="salon")
public class Salon {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String address;
}
