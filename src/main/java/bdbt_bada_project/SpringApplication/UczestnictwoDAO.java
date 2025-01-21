package bdbt_bada_project.SpringApplication;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

@Repository
public class UczestnictwoDAO {

    private final JdbcTemplate jdbcTemplate;

    public UczestnictwoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void zapiszNaWycieczke(int nrCzlonkaKlubu, int nrWycieczki) {
        String sql = "INSERT INTO \"UCZESTNICTWA\" (nr_czlonka_klubu, nr_wycieczki) VALUES (?, ?)";
        jdbcTemplate.update(sql, nrCzlonkaKlubu, nrWycieczki);
    }

    public boolean czyZapisany(int nrCzlonkaKlubu, int nrWycieczki) {
        String sql = "SELECT COUNT(*) FROM \"UCZESTNICTWA\" WHERE nr_czlonka_klubu = ? AND nr_wycieczki = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nrCzlonkaKlubu, nrWycieczki);
        return count != null && count > 0;
    }

    public List<Wyprawa> getWycieczkiDlaUzytkownika(int nrCzlonkaKlubu) {
        String sql = "SELECT w.* " +
                "FROM WYPRAWY w " +
                "JOIN \"UCZESTNICTWA\" u ON w.nr_wycieczki = u.nr_wycieczki " +
                "WHERE u.nr_czlonka_klubu = ?";
        return jdbcTemplate.query(sql, new Object[]{nrCzlonkaKlubu}, BeanPropertyRowMapper.newInstance(Wyprawa.class));
    }

    public void wypiszZWycieczki(int nrCzlonkaKlubu, int nrWycieczki) {
        String sql = "DELETE FROM \"UCZESTNICTWA\" WHERE nr_czlonka_klubu = ? AND nr_wycieczki = ?";
        jdbcTemplate.update(sql, nrCzlonkaKlubu, nrWycieczki);
    }
}
