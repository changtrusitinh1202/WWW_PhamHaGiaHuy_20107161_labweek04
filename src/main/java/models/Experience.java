package models;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Experience {
    private long id;
    private LocalDate toDate;
    private LocalDate fromDate;
    private Candidate candidate;
    private String companyName;
    private String role;
    private String workDescription;
}
