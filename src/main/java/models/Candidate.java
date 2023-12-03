package models;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Candidate {
    private long id;
    private String phone;
    private LocalDate dob;
    private String email;
    private String fullName;
    private Address address;
}
