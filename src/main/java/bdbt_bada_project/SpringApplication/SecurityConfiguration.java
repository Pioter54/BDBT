package bdbt_bada_project.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import
        org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/", "/index").permitAll()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/main").authenticated()

                .antMatchers("/main_admin").access("hasRole('ADMIN')")
                .antMatchers("/adresy_admin").access("hasRole('ADMIN')")
                .antMatchers("/edit_form_adresy").access("hasRole('ADMIN')")
                .antMatchers("/new_form_adresy").access("hasRole('ADMIN')")
                .antMatchers("/new_adres").access("hasRole('ADMIN')")
                .antMatchers("/update_adres").access("hasRole('ADMIN')")
                .antMatchers("/save_adres").access("hasRole('ADMIN')")
                .antMatchers("/edit_adres/{nr_adresu}").access("hasRole('ADMIN')")
                .antMatchers("/delete_adres/{nr_adresu}").access("hasRole('ADMIN')")

                .antMatchers("/main_user").access("hasRole('USER')")

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/index")
                .logoutSuccessUrl("/index")
                .permitAll();
    }
}