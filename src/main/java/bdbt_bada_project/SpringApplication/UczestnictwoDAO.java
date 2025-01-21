package bdbt_bada_project.SpringApplication;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
