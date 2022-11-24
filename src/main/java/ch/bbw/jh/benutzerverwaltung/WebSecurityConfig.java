package ch.bbw.jh.benutzerverwaltung;

import ch.bbw.jh.benutzerverwaltung.user.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The type Web security config.
 */
@Configuration
public class WebSecurityConfig {
    /**
     * The Benutzer service.
     */
    @Autowired
    BenutzerService benutzerService;

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SCryptPasswordEncoder();
    }

    /**
     * Dao authentication provider dao authentication provider.
     *
     * @return the dao authentication provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(this.benutzerService);
        return provider;
    }


    /**
     * Filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
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
                .logout((logout) -> logout.permitAll())
                .logout((logout) -> logout.deleteCookies())
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login")
                );
        return http.build();
    }
}