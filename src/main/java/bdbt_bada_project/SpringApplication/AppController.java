package bdbt_bada_project.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/main_user").setViewName("user/main_user");
        registry.addViewController("/adresy_admin").setViewName("admin/adresy_admin");
        registry.addViewController("/edit_form_adresy").setViewName("admin/edit_form_adresy");
        registry.addViewController("/new_form_adresy").setViewName("admin/new_form_adresy");
    }
    @Controller
    public class DashboardController
    {
        @Autowired
        private AdresDAO dao;
        @RequestMapping("/adresy_admin")
        public String viewHomepage(Model model){
            List<Adres> listAdres = dao.list();
            model.addAttribute("listAdres", listAdres);
            return "admin/adresy_admin";
        }
        @RequestMapping("/new_adres")
        public String showNewForm(Model model){
            Adres adres = new Adres();
            model.addAttribute("adres", adres);
            return "admin/new_form_adresy";
        }
        @RequestMapping(value = "/save_adres", method = RequestMethod.POST)
        public String save(@ModelAttribute("adres") Adres adres){
            dao.save(adres);
            return "redirect:/adresy_admin";
        }
        @RequestMapping("/edit_adres/{nr_adresu}")
        public ModelAndView showEditForm(@PathVariable(name = "nr_adresu") int nr_adresu){
            ModelAndView mav = new ModelAndView("admin/edit_form_adresy");
            Adres adres = dao.get(nr_adresu);
            mav.addObject("adres", adres);
            return mav;
        }
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public String update(@ModelAttribute("adres") Adres adres){
            dao.update(adres);
            return "redirect:/adresy_admin";
        }
        @RequestMapping(value = "/delete_adres/{nr_adresu}")
        public String delete(@PathVariable(name = "nr_adresu") int nr_adresu){
            dao.delete(nr_adresu);
            return "redirect:/adresy_admin";
        }
        @RequestMapping("/main")
        public String defaultAfterLogin
                (HttpServletRequest request) {
            if
            (request.isUserInRole
                    ("ADMIN")) {
                return "redirect:/main_admin";
            }
            else if
            (request.isUserInRole
                            ("USER")) {
                return "redirect:/main_user";
            }
            else
            {
                return "redirect:/index";
            }
        }
    }
    @RequestMapping(value={"/main_admin"})
    public String showAdminPage(Model model) {
        return "admin/main_admin";
    }
    @RequestMapping(value={"/main_user"})
    public String showUserPage(Model model) {
        return "user/main_user";
    }
}