package bdbt_bada_project.SpringApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdresDAOTest extends Object {
    private AdresDAO dao;
    @BeforeEach
    void setUp() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("PIOTR123");
        datasource.setPassword("haslo123");
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new AdresDAO(new JdbcTemplate(datasource));
    }

    @Test
    void list() {
        List<Adres> listAdres = dao.list();
        assertTrue(listAdres.isEmpty());
    }

    @Test
    void save() {
        Adres adres = new Adres(9,"Polska", "Warszawa", "Nowowiejska", 1, 1, 1);
        dao.save(adres);
    }

    @Test
    void get() {
        fail("Not yet implemented");
    }

    @Test
    void update() {
        fail("Not yet implemented");
    }

    @Test
    void delete() {
        fail("Not yet implemented");
    }
}