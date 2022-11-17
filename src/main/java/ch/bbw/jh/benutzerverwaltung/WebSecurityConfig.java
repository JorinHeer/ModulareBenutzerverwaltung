package ch.bbw.jh.benutzerverwaltung;

import ch.bbw.jh.benutzerverwaltung.user.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Autowired
    BenutzerService benutzerService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(this.benutzerService);
        return provider;
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(daoAuthenticationProvider());
        http
                .authorizeHttpRequests()
                        .antMatchers("/", "/index").authenticated()
                        .antMatchers("/edit-profile").authenticated()
                        .antMatchers("/h2-console/**").hasAuthority("ADMIN")
                        .antMatchers("/users", "/get-users").hasAuthority("ADMIN")
                        .antMatchers("/changeusers", "/edit-user").hasAuthority("ADMIN")
                        .anyRequest().hasAuthority("ADMIN")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
}