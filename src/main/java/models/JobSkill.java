package models;

import enums.SkillLevel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobSkill {
    private Job job;
    private Skill skill;
    private SkillLevel skillLevel;
    private String moreInfo;
}
