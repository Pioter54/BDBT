package bdbt_bada_project.SpringApplication;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WyprawaDAO {

    private final JdbcTemplate jdbcTemplate;

    public WyprawaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wyprawa> list() {
        String sql = "SELECT * FROM WYPRAWY";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wyprawa.class));
    }

    public void save(Wyprawa wyprawa) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("wyprawy")
                .usingColumns("data_od", "data_do", "opis", "cena");
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(wyprawa);
        insert.execute(params);
    }

    public Wyprawa get(int nr_wycieczki) {
        String sql = "SELECT * FROM WYPRAWY WHERE nr_wycieczki = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{nr_wycieczki},
                BeanPropertyRowMapper.newInstance(Wyprawa.class));
    }

    public void update(Wyprawa wyprawa) {
        String sql = "UPDATE WYPRAWY SET data_od=:data_od, data_do=:data_do, opis=:opis, cena=:cena WHERE nr_wycieczki=:nr_wycieczki";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(wyprawa);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, params);
    }

    public void delete(int nr_wycieczki) {
        String sql = "DELETE FROM WYPRAWY WHERE nr_wycieczki = ?";
        jdbcTemplate.update(sql, nr_wycieczki);
    }
}
