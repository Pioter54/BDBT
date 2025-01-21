package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public LoginData findByEmail(String email) {
        String sql = "SELECT * FROM DANE_LOGOWANIA WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, BeanPropertyRowMapper.newInstance(LoginData.class));
    }

    public void update(LoginData loginData) {
        String sql = "UPDATE DANE_LOGOWANIA SET email = ?, password = ? WHERE nr_czlonka_klubu = ?";
        jdbcTemplate.update(sql, loginData.getEmail(), loginData.getPassword(), loginData.getNrCzlonkaKlubu());
    }


}
