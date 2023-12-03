package models;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    private long id;
    private CountryCode country;
    private String city;
    private String zipcode;
    private String street;
    private Candidate candidate;
    private String number;
    private Company company;
}
