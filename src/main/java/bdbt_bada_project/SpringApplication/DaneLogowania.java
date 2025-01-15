package bdbt_bada_project.SpringApplication;
import javax.persistence.*;

@Entity
@Table(name = "DANE_LOGOWANIA", schema = "PIOTR123")
public class DaneLogowania {
    @Id
    @Column(name = "NR_CZLONKA_KLUBU")
    private Long nrCzlonkaKlubu;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    // Gettery i settery
    public Long getNrCzlonkaKlubu() { return nrCzlonkaKlubu; }
    public void setNrCzlonkaKlubu(Long nrCzlonkaKlubu) { this.nrCzlonkaKlubu = nrCzlonkaKlubu; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
