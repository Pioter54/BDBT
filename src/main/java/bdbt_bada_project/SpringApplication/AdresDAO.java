package bdbt_bada_project.SpringApplication;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Adres Kraj) {
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
