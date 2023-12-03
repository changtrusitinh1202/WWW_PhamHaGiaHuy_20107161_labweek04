package enums;

public enum SkillType {
    UNSPECIFIC(0),
    TECHNICAL_SKILL(1),
    SOFT_KILL(2);
    private int value;

    private SkillType(int value){
        this.value = value;
    }
}
