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
    void save() {
        Adres adres = new Adres(1,"Polska", "Warszawa", "Nowowiejska", 1, 1, "1");
        dao.save(adres);
    }

    @Test
    void list() {
        List<Adres> listAdres = dao.list();
        assertFalse(listAdres.isEmpty());
    }

    @Test
    void get() {
        int nr_adresu = 3; //ten test przejdzie tylko jeśli adres o takim id istnieje w bazie danych
        Adres adres = dao.get(nr_adresu);
        assertNotNull(adres);
    }

    @Test
    void update() {
        Adres adres = new Adres();
        adres.setNr_adresu(1);
        adres.setKraj("Armenia");
        adres.setMiasto("Kursk");
        adres.setUlica("Jopska");
        adres.setNr_budynku(3);
        adres.setNr_lokalu(4);
        adres.setKod_pocztowy("9947");
        dao.update(adres);
    }

    @Test
    void delete() {
        int nr_adresu = 1;
        dao.delete(nr_adresu);
    }
}