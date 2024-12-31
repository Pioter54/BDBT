package bdbt_bada_project.SpringApplication;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AdresDAO {
    /* Import org.springframework.jd....Template */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public AdresDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
    /* Import java.util.List */ //(zawiera info z bazy danych)
    public List<Adres> list(){
        String sql = "SELECT * FROM ADRESY";

        List<Adres> listAdres = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Adres.class));
        return listAdres;
    }
    public void save(Adres adres) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("adresy").usingColumns("kraj", "miasto", "ulica", "nr_budynku", "nr_lokalu", "kod_pocztowy");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adres);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Adres get(int Nr_adresu) {
        return null;
    }
    /* Update – aktualizacja danych */
    public void update(Adres Kraj) {
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int Nr_adresu) {
    }

}
