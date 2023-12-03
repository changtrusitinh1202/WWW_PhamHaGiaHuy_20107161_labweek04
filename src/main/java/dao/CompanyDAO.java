package dao;

import models.Company;
import models.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CompanyDAO implements DAO<Company> {
    private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Company> list() {
        String sql = "select * from company";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Company.class));
    }

    @Override
    public void create(Company company) {
        String sql = "insert into company VALUES(?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql, company.getId(), company.getAddress().getId(), company.getAbout(),
                company.getName(), company.getEmail(), company.getPhone(), company.getWebUrl());
    }

    @Override
    public Optional<Company> get(long id) {
        String sql = "select * from company where com_id = ?";
        Company company = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Company.class), id);
        return company == null ? Optional.empty() : Optional.of(company);
    }

    @Override
    public void update(Company company, long id) {
        String sql = "update company set address = ?, about = ?, comp_name = ?, email = ?, phone = ?, web_url = ?";
        int insert = jdbcTemplate.update(sql, company.getAddress().getId(), company.getAbout(),
                company.getName(), company.getEmail(), company.getPhone(), company.getWebUrl(), company.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from company where  com_id = ?", id);
    }
}
