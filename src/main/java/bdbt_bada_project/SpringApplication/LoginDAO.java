package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(LoginData loginData) {
        String sql = "INSERT INTO DANE_LOGOWANIA (email, password, nr_czlonka_klubu) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, loginData.getEmail(), loginData.getPassword(), loginData.getNrCzlonkaKlubu());
    }
}
