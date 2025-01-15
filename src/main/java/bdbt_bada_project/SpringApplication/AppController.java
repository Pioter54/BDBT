package bdbt_bada_project.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    }

    @Controller
    public class DashboardController {
        @Autowired
        private AdresDAO adresDAO;

        @Autowired
        private CzlonekKlubuDAO czlonekKlubuDAO;

        @Autowired
        private LoginDAO loginDAO;

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

        @RequestMapping("/edit_czlonek/{nr_czlonka_klubu}")
        public ModelAndView showEditCzlonekForm(@PathVariable(name = "nr_czlonka_klubu") int nr_czlonka_klubu) {
            ModelAndView mav = new ModelAndView("admin/edit_form_czlonkowie_klubu");
            CzlonekKlubu czlonek = czlonekKlubuDAO.get(nr_czlonka_klubu);
            mav.addObject("czlonek", czlonek);
            return mav;
        }

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


        @RequestMapping(value = "/register/save", method = RequestMethod.POST)
        public String registerUser(@ModelAttribute("userRegistration") UserRegistrationDTO userRegistration) {
            // Zapisz adres i uzyskaj jego ID
            Adres adres = userRegistration.getAdres();
            int nrAdresu = adresDAO.saveAndReturnId(adres);

            // Powiąż nr_adresu z członkiem klubu
            CzlonekKlubu czlonek = userRegistration.getCzlonek();
            czlonek.setNr_adresu(nrAdresu);

            // Zapisz członka klubu i uzyskaj jego ID
            int nrCzlonkaKlubu = czlonekKlubuDAO.saveAndReturnId(czlonek);

            // Zapisz dane logowania
            LoginData loginData = userRegistration.getLoginData();
            loginData.setNrCzlonkaKlubu(nrCzlonkaKlubu);
            loginDAO.save(loginData);

            return "redirect:/login";
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
