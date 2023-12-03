package models;

import enums.SkillType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Skill {
    private long id;
    private String skillName;
    private String skillDescription;
    private SkillType skillType;
}
