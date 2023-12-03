package dao;

import models.Candidate;
import models.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JobDAO implements DAO<Job>{
    private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Job> list() {
        String sql = "select * from job";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Job.class));
    }

    @Override
    public void create(Job job) {
        String sql = "insert into job VALUES(?,?,?)";
        int insert = jdbcTemplate.update(sql, job.getCompany().getId(), job.getDescription(), job.getName());
    }

    @Override
    public Optional<Job> get(long id) {
        String sql = "select * from job where job_id = ?";
        Job job = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Job.class), id);
        return job == null ? Optional.empty() : Optional.of(job);
    }

    @Override
    public void update(Job job, long id) {
        String sql = "update job set company = ?, job_desc = ?, job_name = ? where job_id = ?";
        int update = jdbcTemplate.update(sql, job.getCompany().getId(), job.getDescription(), job.getName(), job.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from job where job_id = ?", id);
    }
}
