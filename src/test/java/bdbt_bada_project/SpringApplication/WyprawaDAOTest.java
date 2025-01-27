package bdbt_bada_project.SpringApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WyprawaDAOTest {
    private WyprawaDAO dao;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("PIOTR123");
        datasource.setPassword("haslo123");
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new WyprawaDAO(new JdbcTemplate(datasource));
    }

    @Test
    void save() {
        Wyprawa wyprawa = new Wyprawa(1, (LocalDate.of(2024, 12, 1)), (LocalDate.of(2024, 12, 1)), "Wycieczka w góry", 1999.99);
        dao.save(wyprawa);
    }

    @Test
    void list() {
        List<Wyprawa> listWyprawy = dao.list();
        assertFalse(listWyprawy.isEmpty());
    }

    @Test
    void get() {
        int nr_wycieczki = 1; // Ten test przejdzie tylko jeśli wyprawa o takim ID istnieje w bazie danych
        Wyprawa wyprawa = dao.get(nr_wycieczki);
        assertNotNull(wyprawa);
    }

    @Test
    void update() {
        Wyprawa wyprawa = new Wyprawa();
        wyprawa.setNr_wycieczki(1);
        wyprawa.setData_od((LocalDate.of(2024, 12, 1)));
        wyprawa.setData_do((LocalDate.of(2024, 12, 1)));
        wyprawa.setOpis("Zaktualizowana wycieczka na Mazury");
        wyprawa.setCena(2499.99);
        dao.update(wyprawa);
    }

    @Test
    void delete() {
        int nr_wycieczki = 1; // Ten test usunie rekord o wskazanym ID
        dao.delete(nr_wycieczki);
    }
}
