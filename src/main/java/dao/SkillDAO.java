package dao;

import models.Company;
import models.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class SkillDAO implements  DAO<Skill> {
    private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Skill> list() {
        String sql = "select * from skill";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Skill.class));
    }

    @Override
    public void create(Skill skill) {
        String sql = "insert into skill VALUES(?,?,?,?)";
        int insert = jdbcTemplate.update(sql, skill.getId(), skill.getSkillType(), skill.getSkillName(), skill.getSkillDescription());
    }

    @Override
    public Optional<Skill> get(long id) {
        String sql = "select * from skill where skill_id = ?";
        Skill skill = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Skill.class), id);
        return skill == null ? Optional.empty() : Optional.of(skill);
    }

    @Override
    public void update(Skill skill, long id) {
        String sql = "update skill set skill_type = ?, skill_name = ?, skill_desc = ? where skill_id = ?";
        int update = jdbcTemplate.update(sql, skill.getSkillType(), skill.getSkillName(), skill.getSkillDescription(), skill.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from skill where skill_id", id);
    }
}
