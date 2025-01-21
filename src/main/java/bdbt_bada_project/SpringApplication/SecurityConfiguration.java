package bdbt_bada_project.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import
        org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, 'true' FROM DANE_LOGOWANIA WHERE email = ?")
                .authoritiesByUsernameQuery(
                        "SELECT l.email, 'ROLE_USER' " +
                                "FROM DANE_LOGOWANIA l " +
                                "WHERE l.email = ?")
                .passwordEncoder(passwordEncoder());

        // Konfiguracja administratorów w pamięci
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin")) // Szyfrowane hasło dla admina
                .roles("ADMIN");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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

                .antMatchers("/czlonkowie_klubu_admin").access("hasRole('ADMIN')")
                .antMatchers("/edit_form_czlonkowie_klubu").access("hasRole('ADMIN')")
                .antMatchers("/new_form_czlonkowie_klubu").access("hasRole('ADMIN')")
                .antMatchers("/new_czlonek").access("hasRole('ADMIN')")
                .antMatchers("/update_czlonek").access("hasRole('ADMIN')")
                .antMatchers("/save_czlonek").access("hasRole('ADMIN')")
                .antMatchers("/edit_czlonek/{nr_czlonka_klubu}").access("hasRole('ADMIN')")
                .antMatchers("/delete_czlonek/{nrCzlonkaKlubu}").access("hasRole('ADMIN')")
                .antMatchers("/new_user_with_address").access("hasRole('ADMIN')")
                .antMatchers("/save_user_with_address").access("hasRole('ADMIN')")



                .antMatchers("/wyprawy_admin").access("hasRole('ADMIN')")
                .antMatchers("/edit_form_wyprawa").access("hasRole('ADMIN')")
                .antMatchers("/new_form_wyprawa").access("hasRole('ADMIN')")
                .antMatchers("/new_wyprawa").access("hasRole('ADMIN')")
                .antMatchers("/update_wyprawa").access("hasRole('ADMIN')")
                .antMatchers("/save_wyprawa").access("hasRole('ADMIN')")
                .antMatchers("/edit_wyprawa/{nr_wycieczki}").access("hasRole('ADMIN')")
                .antMatchers("/delete_wyprawa/{nr_wycieczki}").access("hasRole('ADMIN')")

                .antMatchers("/main_user").access("hasRole('USER')")
                .antMatchers("/edit_my_data").hasRole("USER")
                .antMatchers("/update_my_data").hasRole("USER")
                .antMatchers("/edit_my_data", "/update_my_data").hasRole("USER")
                .antMatchers("/wyprawy_user", "/zapisz_na_wycieczke/**").hasRole("USER")
                .antMatchers("/moje_wycieczki").hasRole("USER")





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