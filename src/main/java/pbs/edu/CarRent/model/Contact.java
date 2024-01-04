package pbs.edu.CarRent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact {
    private String name;
    private String subject;
    private String content;
    private String from;
}
