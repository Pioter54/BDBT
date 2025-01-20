package bdbt_bada_project.SpringApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CzlonekKlubuDAOTest {
    private CzlonekKlubuDAO dao;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("PIOTR123");
        datasource.setPassword("haslo123");
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new CzlonekKlubuDAO(new JdbcTemplate(datasource));
    }

    @Test
    void save() {
        CzlonekKlubu czlonek = new CzlonekKlubu(1, "Wanessssa", "Fochniak", "12345678901", LocalDate.of(1980, 1, 1), "M", "123456789", LocalDate.of(2023, 1, 1), null, 1);
        dao.save(czlonek);

        dao.save(czlonek);
    }

    @Test
    void list() {
        List<CzlonekKlubu> listCzlonkowie = dao.list();
        assertFalse(listCzlonkowie.isEmpty());
    }

    @Test
    void get() {
        int nrCzlonkaKlubu = 1; // Test przejdzie tylko, jeśli członek o takim ID istnieje w bazie danych
        CzlonekKlubu czlonek = dao.get(nrCzlonkaKlubu);
        assertNotNull(czlonek);
    }

    @Test
    void update() {
        CzlonekKlubu czlonek = new CzlonekKlubu();
        czlonek.setNr_czlonka_klubu(1);
        czlonek.setImie("Angelika");
        czlonek.setNazwisko("Chrząszcz");
        czlonek.setPesel("98765432109");
        czlonek.setData_urodzenia(LocalDate.of(1990, 5, 15));
        czlonek.setPlec("K");
        czlonek.setTelefon("987654321");
        czlonek.setData_dolaczenia(LocalDate.of(2022, 12, 1));
        czlonek.setData_odejscia(LocalDate.of(2024, 12, 1));
        czlonek.setNr_adresu(1);
        dao.update(czlonek);
    }

    @Test
    void delete() {
        int nrCzlonkaKlubu = 22;
        dao.delete(nrCzlonkaKlubu);
    }
}
