package models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {
    private long id;
    private String about;
    private String email;
    private String name;
    private String phone;
    private String webUrl;
    private Address address;
}
