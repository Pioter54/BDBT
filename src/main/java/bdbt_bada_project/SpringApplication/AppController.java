package bdbt_bada_project.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppController implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("registration");
        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/main_user").setViewName("user/main_user");
        registry.addViewController("/adresy_admin").setViewName("admin/adresy_admin");
        registry.addViewController("/edit_form_adresy").setViewName("admin/edit_form_adresy");
        registry.addViewController("/new_form_adresy").setViewName("admin/new_form_adresy");
        registry.addViewController("/czlonkowie_klubu_admin").setViewName("admin/czlonkowie_klubu_admin");
        registry.addViewController("/edit_form_czlonkowie").setViewName("admin/edit_form_czlonkowie_klubu");
        registry.addViewController("/new_form_czlonkowie").setViewName("admin/new_form_czlonkowie_klubu");
        registry.addViewController("/wyprawy_admin").setViewName("admin/wyprawy_admin");
        registry.addViewController("/edit_form_wyprawy").setViewName("admin/edit_form_wyprawy");
        registry.addViewController("/new_form_wyprawy").setViewName("admin/new_form_wyprawy");
        registry.addViewController("/new_user_with_address").setViewName("admin/new_user_with_address");
        registry.addViewController("/edit_user_with_address").setViewName("admin/edit_form_czlonkowie_klubu");
    }

    @Controller
    public class DashboardController {
        @Autowired
        private AdresDAO adresDAO;

        @Autowired
        private CzlonekKlubuDAO czlonekKlubuDAO;

        @Autowired
        private LoginDAO loginDAO;

        @Autowired
        private WyprawaDAO wyprawaDAO;

        @Autowired
        private UczestnictwoDAO uczestnictwoDAO;

        @RequestMapping("/adresy_admin")
        public String viewAdresy(Model model) {
            List<Adres> listAdres = adresDAO.list();
            model.addAttribute("listAdres", listAdres);
            return "admin/adresy_admin";
        }

        @RequestMapping("/new_adres")
        public String showNewAdresForm(Model model) {
            Adres adres = new Adres();
            model.addAttribute("adres", adres);
            return "admin/new_form_adresy";
        }

        @RequestMapping(value = "/save_adres", method = RequestMethod.POST)
        public String saveAdres(@ModelAttribute("adres") Adres adres) {
            adresDAO.save(adres);
            return "redirect:/adresy_admin";
        }

        @RequestMapping("/edit_adres/{nr_adresu}")
        public ModelAndView showEditAdresForm(@PathVariable(name = "nr_adresu") int nr_adresu) {
            ModelAndView mav = new ModelAndView("admin/edit_form_adresy");
            Adres adres = adresDAO.get(nr_adresu);
            mav.addObject("adres", adres);
            return mav;
        }

        @RequestMapping(value = "/update_adres", method = RequestMethod.POST)
        public String updateAdres(@ModelAttribute("adres") Adres adres) {
            adresDAO.update(adres);
            return "redirect:/adresy_admin";
        }

        @RequestMapping(value = "/delete_adres/{nr_adresu}")
        public String deleteAdres(@PathVariable(name = "nr_adresu") int nr_adresu) {
            adresDAO.delete(nr_adresu);
            return "redirect:/adresy_admin";
        }

        @RequestMapping("/czlonkowie_klubu_admin")
        public String viewCzlonkowieKlubu(Model model) {
            List<CzlonekKlubu> listCzlonkowie = czlonekKlubuDAO.list();
            model.addAttribute("listCzlonkowie", listCzlonkowie);
            return "admin/czlonkowie_klubu_admin";
        }

        @RequestMapping("/new_czlonek")
        public String showNewCzlonekForm(Model model) {
            CzlonekKlubu czlonek = new CzlonekKlubu();
            model.addAttribute("czlonek", czlonek);
            return "admin/new_form_czlonkowie_klubu";
        }

        @RequestMapping(value = "/save_czlonek", method = RequestMethod.POST)
        public String saveCzlonek(@ModelAttribute("czlonek") CzlonekKlubu czlonek) {
            czlonekKlubuDAO.save(czlonek);
            return "redirect:/czlonkowie_klubu_admin";
        }

//        @RequestMapping("/edit_czlonek/{nr_czlonka_klubu}")
//        public ModelAndView showEditCzlonekForm(@PathVariable(name = "nr_czlonka_klubu") int nr_czlonka_klubu) {
//            ModelAndView mav = new ModelAndView("admin/edit_form_czlonkowie_klubu");
//            CzlonekKlubu czlonek = czlonekKlubuDAO.get(nr_czlonka_klubu);
//            mav.addObject("czlonek", czlonek);
//            return mav;
//        }

        @RequestMapping(value = "/update_czlonek", method = RequestMethod.POST)
        public String updateCzlonek(@ModelAttribute("czlonek") CzlonekKlubu czlonek) {
            czlonekKlubuDAO.update(czlonek);
            return "redirect:/czlonkowie_klubu_admin";
        }

        @RequestMapping(value = "/delete_czlonek/{nrCzlonkaKlubu}")
        public String deleteCzlonek(@PathVariable(name = "nrCzlonkaKlubu") int nrCzlonkaKlubu) {
            czlonekKlubuDAO.delete(nrCzlonkaKlubu);
            return "redirect:/czlonkowie_klubu_admin";
        }

        @RequestMapping("/main")
        public String defaultAfterLogin(HttpServletRequest request) {
            if (request.isUserInRole("ADMIN")) {
                return "redirect:/main_admin";
            } else if (request.isUserInRole("USER")) {
                return "redirect:/main_user";
            } else {
                return "redirect:/index";
            }
        }

        @RequestMapping("/register")
        public String showRegistrationForm(Model model) {
            UserRegistrationDTO userRegistration = new UserRegistrationDTO();
            userRegistration.setCzlonek(new CzlonekKlubu());
            userRegistration.setAdres(new Adres());
            userRegistration.setLoginData(new LoginData());
            model.addAttribute("userRegistration", userRegistration);
            return "registration";
        }


        @Autowired
        private PasswordEncoder passwordEncoder;

        @RequestMapping(value = "/register/save", method = RequestMethod.POST)
        public String registerUser(@ModelAttribute("userRegistration") UserRegistrationDTO userRegistration, Model model) {
            // Sprawdzanie, czy email już istnieje
            String email = userRegistration.getLoginData().getEmail();
            try {
                loginDAO.findByEmail(email);
                model.addAttribute("errorMessage", "Podany adres e-mail jest już zarejestrowany.");
                return "registration"; // Powrót do formularza rejestracji z komunikatem błędu
            } catch (EmptyResultDataAccessException e) {
                // Email nie istnieje, można kontynuować
            }

            // Zapisz adres i uzyskaj jego ID
            Adres adres = userRegistration.getAdres();
            int nrAdresu = adresDAO.saveAndReturnId(adres);

            // Powiąż nr_adresu z członkiem klubu
            CzlonekKlubu czlonek = userRegistration.getCzlonek();
            czlonek.setNr_adresu(nrAdresu);

            // Zapisz członka klubu i uzyskaj jego ID
            int nrCzlonkaKlubu = czlonekKlubuDAO.saveAndReturnId(czlonek);

            // Szyfruj hasło przed zapisaniem
            LoginData loginData = userRegistration.getLoginData();
            String encodedPassword = passwordEncoder.encode(loginData.getPassword());
            loginData.setPassword(encodedPassword);

            // Przypisz nr_czlonka_klubu i zapisz dane logowania
            loginData.setNrCzlonkaKlubu(nrCzlonkaKlubu);
            loginDAO.save(loginData);

            return "redirect:/login";
        }

        @RequestMapping("/wyprawy_admin")
        public String viewWyprawy(Model model) {
            List<Wyprawa> listWyprawy = wyprawaDAO.list();
            model.addAttribute("listWyprawy", listWyprawy);
            return "admin/wyprawy_admin";
        }

        @RequestMapping("/new_wyprawa")
        public String showNewWyprawaForm(Model model) {
            Wyprawa wyprawa = new Wyprawa();
            model.addAttribute("wyprawa", wyprawa);
            return "admin/new_form_wyprawy";
        }

        @RequestMapping(value = "/save_wyprawa", method = RequestMethod.POST)
        public String saveWyprawa(@ModelAttribute("wyprawa") Wyprawa wyprawa) {
            wyprawaDAO.save(wyprawa);
            return "redirect:/wyprawy_admin";
        }

        @RequestMapping("/edit_wyprawa/{nr_wycieczki}")
        public ModelAndView showEditWyprawaForm(@PathVariable(name = "nr_wycieczki") int nr_wycieczki) {
            ModelAndView mav = new ModelAndView("admin/edit_form_wyprawy");
            Wyprawa wyprawa = wyprawaDAO.get(nr_wycieczki);
            mav.addObject("wyprawa", wyprawa);
            return mav;
        }

        @RequestMapping(value = "/update_wyprawa", method = RequestMethod.POST)
        public String updateWyprawa(@ModelAttribute("wyprawa") Wyprawa wyprawa) {
            wyprawaDAO.update(wyprawa);
            return "redirect:/wyprawy_admin";
        }

        @RequestMapping(value = "/delete_wyprawa/{nr_wycieczki}")
        public String deleteWyprawa(@PathVariable(name = "nr_wycieczki") int nr_wycieczki) {
            wyprawaDAO.delete(nr_wycieczki);
            return "redirect:/wyprawy_admin";
        }

        @RequestMapping("/new_user_with_address")
        public String showNewUserWithAddressForm(Model model) {
            UserRegistrationDTO userRegistration = new UserRegistrationDTO();
            userRegistration.setCzlonek(new CzlonekKlubu());
            userRegistration.setAdres(new Adres());
            model.addAttribute("userRegistration", userRegistration);
            return "admin/new_user_with_address";
        }

        @RequestMapping(value = "/save_user_with_address", method = RequestMethod.POST)
        public String saveUserWithAddress(@ModelAttribute("userRegistration") UserRegistrationDTO userRegistration) {
            // Zapisz adres i uzyskaj ID
            Adres adres = userRegistration.getAdres();
            int nrAdresu = adresDAO.saveAndReturnId(adres);

            // Powiąż ID adresu z użytkownikiem
            CzlonekKlubu czlonek = userRegistration.getCzlonek();
            czlonek.setNr_adresu(nrAdresu);

            // Zapisz użytkownika
            czlonekKlubuDAO.save(czlonek);

            return "redirect:/czlonkowie_klubu_admin";
        }

        @RequestMapping("/edit_czlonek/{nrCzlonkaKlubu}")
        public String showEditUserWithAddressForm(@PathVariable int nrCzlonkaKlubu, Model model) {
            CzlonekKlubu czlonek = czlonekKlubuDAO.get(nrCzlonkaKlubu);
            Adres adres = adresDAO.get(czlonek.getNr_adresu());

            UserRegistrationDTO userRegistration = new UserRegistrationDTO();
            userRegistration.setCzlonek(czlonek);
            userRegistration.setAdres(adres);

            model.addAttribute("userRegistration", userRegistration);
            return "admin/edit_form_czlonkowie_klubu"; // Zmieniona nazwa widoku
        }


        @RequestMapping(value = "/update_user_with_address", method = RequestMethod.POST)
        public String updateUserWithAddress(@ModelAttribute("userRegistration") UserRegistrationDTO userRegistration) {
            Adres adres = userRegistration.getAdres();
            int nrAdresu;

            if (adresDAO.get(adres.getNr_adresu()) != null) {
                adresDAO.update(adres);
                nrAdresu = adres.getNr_adresu(); // Pobieramy istniejący nr_adresu
            } else {
                nrAdresu = adresDAO.saveAndReturnId(adres); // Zapisujemy nowy adres
            }

            // PRZYPISZ POPRAWNE nr_adresu DO CZŁONKA
            userRegistration.getCzlonek().setNr_adresu(nrAdresu);

            // Aktualizujemy członka klubu
            czlonekKlubuDAO.update(userRegistration.getCzlonek());




            return "redirect:/czlonkowie_klubu_admin";
        }

        @RequestMapping("/edit_my_data")
        public String showEditMyDataForm(Model model, Principal principal) {
            String email = principal.getName(); // Pobierz email zalogowanego użytkownika
            LoginData loginData = loginDAO.findByEmail(email); // Znajdź dane logowania

            if (loginData != null) {
                CzlonekKlubu czlonek = czlonekKlubuDAO.get(loginData.getNrCzlonkaKlubu()); // Pobierz dane członka
                Adres adres = adresDAO.get(czlonek.getNr_adresu()); // Pobierz adres

                UserRegistrationDTO userRegistration = new UserRegistrationDTO();
                userRegistration.setCzlonek(czlonek);
                userRegistration.setAdres(adres);
                userRegistration.setLoginData(loginData);

                model.addAttribute("userRegistration", userRegistration);
                return "user/edit_my_data";
            }

            return "redirect:/main_user"; // Jeśli użytkownik nie został znaleziony
        }

        @RequestMapping(value = "/update_my_data", method = RequestMethod.POST)
        public String updateMyData(@ModelAttribute("userRegistration") UserRegistrationDTO userRegistration, Principal principal) {
            String email = principal.getName(); // Pobierz email zalogowanego użytkownika
            LoginData loginData = loginDAO.findByEmail(email); // Znajdź dane logowania

            if (loginData != null) {
                // Aktualizacja adresu
                Adres adres = userRegistration.getAdres();
                adresDAO.update(adres);

                // Aktualizacja danych członka klubu
                userRegistration.getCzlonek().setNr_adresu(adres.getNr_adresu());
                czlonekKlubuDAO.update(userRegistration.getCzlonek());

                // Aktualizacja emaila i hasła
                LoginData updatedLoginData = userRegistration.getLoginData();
                loginData.setEmail(updatedLoginData.getEmail());

                // Aktualizacja hasła, jeśli zostało podane
                if (updatedLoginData.getPassword() != null && !updatedLoginData.getPassword().isEmpty()) {
                    String encodedPassword = passwordEncoder.encode(updatedLoginData.getPassword());
                    loginData.setPassword(encodedPassword);
                }

                loginDAO.update(loginData);

                return "redirect:/main_user";
            }

            return "redirect:/main_user"; // Jeśli użytkownik nie został znaleziony
        }

        @RequestMapping("/wyprawy_user")
        public String viewWyprawyDlaUzytkownika(Model model, Principal principal) {
            String email = principal.getName(); // Pobierz email zalogowanego użytkownika
            LoginData loginData = loginDAO.findByEmail(email);

            if (loginData != null) {
                int nrCzlonkaKlubu = loginData.getNrCzlonkaKlubu();

                // Pobierz listę wszystkich wycieczek
                List<Wyprawa> listWyprawy = wyprawaDAO.list();

                // Dodaj informacje o zapisach użytkownika
                Map<Integer, Boolean> zapisaneWycieczki = new HashMap<>();
                for (Wyprawa wyprawa : listWyprawy) {
                    zapisaneWycieczki.put(wyprawa.getNr_wycieczki(), uczestnictwoDAO.czyZapisany(nrCzlonkaKlubu, wyprawa.getNr_wycieczki()));
                }

                model.addAttribute("listWyprawy", listWyprawy);
                model.addAttribute("zapisaneWycieczki", zapisaneWycieczki);
            }

            return "user/wyprawy_user";
        }


        @RequestMapping("/zapisz_na_wycieczke/{nrWycieczki}")
        public String zapiszNaWycieczke(@PathVariable int nrWycieczki, Principal principal) {
            String email = principal.getName(); // Pobierz email zalogowanego użytkownika
            LoginData loginData = loginDAO.findByEmail(email);

            if (loginData != null) {
                int nrCzlonkaKlubu = loginData.getNrCzlonkaKlubu();

                // Sprawdź, czy użytkownik już jest zapisany
                if (!uczestnictwoDAO.czyZapisany(nrCzlonkaKlubu, nrWycieczki)) {
                    uczestnictwoDAO.zapiszNaWycieczke(nrCzlonkaKlubu, nrWycieczki);
                }
            }

            return "redirect:/wyprawy_user";
        }

        @RequestMapping("/moje_wycieczki")
        public String mojeWycieczki(Model model, Principal principal) {
            String email = principal.getName(); // Pobierz email zalogowanego użytkownika
            LoginData loginData = loginDAO.findByEmail(email);

            if (loginData != null) {
                int nrCzlonkaKlubu = loginData.getNrCzlonkaKlubu();
                List<Wyprawa> mojeWycieczki = uczestnictwoDAO.getWycieczkiDlaUzytkownika(nrCzlonkaKlubu);
                model.addAttribute("mojeWycieczki", mojeWycieczki);
            }

            return "user/moje_wycieczki";
        }

        @RequestMapping("/wypisz_z_wycieczki/{nrWycieczki}")
        public String wypiszZWycieczki(@PathVariable int nrWycieczki, Principal principal) {
            String email = principal.getName(); // Pobierz email zalogowanego użytkownika
            LoginData loginData = loginDAO.findByEmail(email);

            if (loginData != null) {
                int nrCzlonkaKlubu = loginData.getNrCzlonkaKlubu();
                uczestnictwoDAO.wypiszZWycieczki(nrCzlonkaKlubu, nrWycieczki);
            }

            return "redirect:/moje_wycieczki";
        }

        @GetMapping("/session-expired")
        public String sessionExpired() {
            return "session-expired"; // Nazwa pliku HTML bez rozszerzenia
        }
    }

    @RequestMapping(value = {"/main_admin"})
    public String showAdminPage(Model model) {
        return "admin/main_admin";
    }

    @RequestMapping(value = {"/main_user"})
    public String showUserPage(Model model) {
        return "user/main_user";
    }


}
