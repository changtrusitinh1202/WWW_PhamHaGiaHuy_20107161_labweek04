package dao;

import models.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class AddressDAO implements DAO<Address>{
    private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Address> list() {
        String sql = "SELECT * from Address";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Address.class));
    }

    @Override
    public void create(Address address) {
        String sql = "Insert into address VALUES(?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql, address.getId(), address.getCountry(), address.getZipcode(), address.getNumber(), address.getCity(), address.getStreet());
    }


    @Override
    public Optional<Address> get(long id) {
        String sql = "Select * from address where add_id = ?";
        Address address = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Address.class), id);

        return address == null ? Optional.empty() : Optional.of(address);
    }

    @Override
    public void update(Address address, long id) {
        String sql = "update address set country = ?, zipcode = ?, number = ?, city = ?, street = ?";
        int update  = jdbcTemplate.update(sql,address.getCountry(), address.getZipcode(), address.getNumber(), address.getCity(), address.getStreet(), address.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from address where add_id = ?", id);
    }
}
