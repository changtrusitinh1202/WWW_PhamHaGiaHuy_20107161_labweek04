package dao;

import models.Experience;
import models.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ExperienceDAO implements DAO<Experience> {
    private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Experience> list() {
        String sql = "select * from experience";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Experience.class));
    }

    @Override
    public void create(Experience experience) {
        String sql = "insert into experience VALUES(?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql, experience.getId(), experience.getFromDate(), experience.getToDate(),experience.getCandidate().getId()
        ,experience.getRole(), experience.getCompanyName(), experience.getWorkDescription());
    }

    @Override
    public Optional<Experience> get(long id) {
        String sql = "select * from experience where exp_id = ?";
        Experience experience = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Experience.class), id);
        return experience == null ? Optional.empty() : Optional.of(experience);
    }

    @Override
    public void update(Experience experience, long id) {
        String sql = "update experience set from_date = ?, to_date = ?, can_id = ?, role = ?, company = ?, workdesc = ? where exp_id = ?";
        int insert = jdbcTemplate.update(sql, experience.getFromDate(), experience.getToDate(),experience.getCandidate().getId()
                ,experience.getRole(), experience.getCompanyName(), experience.getWorkDescription(), experience.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from experience where exp_id = ?", id);
    }
}
