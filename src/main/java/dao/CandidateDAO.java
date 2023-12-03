package dao;

import models.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CandidateDAO implements DAO<Candidate> {
    private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Candidate> list() {
        String sql = "select * from candidate";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Candidate.class));
    }

    @Override
    public void create(Candidate candidate) {
        String sql = "insert into candidate VALUES(?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql, candidate.getId(), candidate.getDob(), candidate.getAddress().getId(),candidate.getPhone(), candidate.getEmail(), candidate.getFullName());
    }

    @Override
    public Optional<Candidate> get(long id) {
        String sql = "select * from candidate where can_id = ?";
        Candidate candidate = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Candidate.class), id);
        return candidate == null ? Optional.empty() : Optional.of(candidate);
    }

    @Override
    public void update(Candidate candidate, long id) {
        String sql = "update candidate set dob = ?, address = ?, phone = ?, email = ?, fulname = ? where can_id = ?";
        int update = jdbcTemplate.update(sql, candidate.getDob(), candidate.getAddress(), candidate.getPhone(), candidate.getEmail(), candidate.getFullName(), candidate.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from candidate where can_id = ?", id);
    }
}
