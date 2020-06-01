package pl.mzuchnik.springsecurityhomework4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser(new User("admin",
                        passwordEncoder().encode("admin123"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))))
                .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public SuperUltraMegaPasswordEncoder passwordEncoder()
    {
        return new SuperUltraMegaPasswordEncoder();
    }
}
