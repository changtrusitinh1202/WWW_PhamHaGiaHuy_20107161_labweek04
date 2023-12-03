package models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job {
    private long id;
    private String name;
    private Company company;
    private String description;
}
