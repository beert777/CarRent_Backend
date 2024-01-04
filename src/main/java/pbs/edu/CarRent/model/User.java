package pbs.edu.CarRent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="user")
public class User {
    public User(String firstName, String lastName, String email, String phone, String address, Date birthdate, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Date birthdate;
    private String username;
    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
