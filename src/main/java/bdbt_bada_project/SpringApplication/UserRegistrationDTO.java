package bdbt_bada_project.SpringApplication;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class UserRegistrationDTO {
    private CzlonekKlubu czlonek;
    private Adres adres;
    private LoginData loginData;

    // Gettery i Settery
    public CzlonekKlubu getCzlonek() {
        return czlonek;
    }
    public void setCzlonek(CzlonekKlubu czlonek) {
        this.czlonek = czlonek;
    }

    public Adres getAdres() {
        return adres;
    }
    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public LoginData getLoginData() {
        return loginData;
    }
    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

}
