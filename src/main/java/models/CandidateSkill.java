package models;

import enums.SkillLevel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidateSkill {
    private Skill skill;
    private Candidate candidate;
    private SkillLevel skillLevel;
    private String moreInfo;
}
