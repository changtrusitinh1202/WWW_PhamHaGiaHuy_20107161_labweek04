package dao;

import models.CandidateSkill;
import models.Experience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CandidateSkillDAO implements DAO<CandidateSkill> {
    private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<CandidateSkill> list() {
        String sql = "select * from candidate_skill";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CandidateSkill.class));
    }

    @Override
    public void create(CandidateSkill candidateSkill) {
        String sql = "insert into candidate_skill VALUES(?,?,?,?)";
        int insert = jdbcTemplate.update(sql, candidateSkill.getCandidate().getId(), candidateSkill.getSkill().getId()
                                            , candidateSkill.getSkillLevel(), candidateSkill.getMoreInfo());
    }

    @Override
    public Optional<CandidateSkill> get(long id) {
        String sql = "select * from candidate_skill where skill_id = ? and can_id = ?";
        CandidateSkill candidateSkill = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CandidateSkill.class), id);
        return candidateSkill == null ? Optional.empty() : Optional.of(candidateSkill);
    }

    @Override
    public void update(CandidateSkill candidateSkill, long id) {
        String sql = "update candidate_skill set skill_level = ?, more_infos = ? where where skill_id = ? and can_id = ?";
        int insert = jdbcTemplate.update(sql, candidateSkill.getSkillLevel(), candidateSkill.getMoreInfo()
                , candidateSkill.getCandidate().getId(), candidateSkill.getSkill().getId());
    }

    @Override
    public void delete(long id) {

    }


    public void delete(long id1, long id2) {

    }
}
