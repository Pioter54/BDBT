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

    @RequestMapping("/new_user_with_address")
    public String showNewUserWithAddressForm(Model model) {
        UserRegistrationDTO userRegistration = new UserRegistrationDTO();
        userRegistration.setCzlonek(new CzlonekKlubu());
        userRegistration.setAdres(new Adres());
        model.addAttribute("userRegistration", userRegistration);
        return "new_form_user_address";
    }

}
