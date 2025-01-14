// CzlonekKlubuDAO.java
package bdbt_bada_project.SpringApplication;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CzlonekKlubuDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CzlonekKlubuDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CzlonekKlubu> list() {
        String sql = "SELECT * FROM CZLONKOWIE_KLUBU";
        List<CzlonekKlubu> listCzlonekKluby = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(CzlonekKlubu.class));
        return listCzlonekKluby;
    }

    public void save(CzlonekKlubu czlonekKlubu) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("czlonkowie_klubu").usingColumns("pesel", "imie", "nazwisko", "data_urodzenia", "plec", "telefon", "data_dolaczenia", "data_odejscia", "nr_adresu");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(czlonekKlubu);
        insertActor.execute(param);
    }

    public CzlonekKlubu get(int Nr_czlonka_klubu) {
        Object[] args = {Nr_czlonka_klubu};
        String sql = "SELECT * FROM CZLONKOWIE_KLUBU WHERE nr_czlonka_klubu = " + args[0];
        CzlonekKlubu czlonekKlubu = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(CzlonekKlubu.class));
        return czlonekKlubu;
    }

    public void update(CzlonekKlubu czlonekKlubu) {
        String sql = "UPDATE CZLONKOWIE_KLUBU SET pesel=:pesel, imie=:imie, nazwisko=:nazwisko, data_urodzenia=:data_urodzenia, plec=:plec, telefon=:telefon, data_dolaczenia=:data_dolaczenia, data_odejscia=:data_odejscia, nr_adresu=:nr_adresu WHERE nr_czlonka_klubu=:nr_czlonka_klubu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(czlonekKlubu);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int Nr_czlonka_klubu) {
        String sql = "DELETE FROM CZLONKOWIE_KLUBU WHERE nr_czlonka_klubu = ?";
        jdbcTemplate.update(sql, Nr_czlonka_klubu);
    }
}
