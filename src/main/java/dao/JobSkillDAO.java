package dao;

import models.CandidateSkill;
import models.JobSkill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JobSkillDAO implements DAO<JobSkill> {
    private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<JobSkill> list() {
        String sql = "select * from job_skill";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobSkill.class));
    }

    @Override
    public void create(JobSkill jobSkill) {
        String sql = "insert into job_skill VALUES(?,?,?,?)";
        int insert = jdbcTemplate.update(sql, jobSkill.getJob().getId(), jobSkill.getSkill().getId()
                , jobSkill.getSkillLevel(), jobSkill.getMoreInfo());
    }

    @Override
    public Optional<JobSkill> get(long id) {
        String sql = "select * from candidate_skill where skill_id = ? and can_id = ?";
        JobSkill jobSkill = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JobSkill.class), id);
        return jobSkill == null ? Optional.empty() : Optional.of(jobSkill);
    }

    @Override
    public void update(JobSkill jobSkill, long id) {

    }

    @Override
    public void delete(long id) {

    }
}
