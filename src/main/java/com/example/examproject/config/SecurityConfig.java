package com.example.examproject.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;




@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/css/**").permitAll()
                .antMatchers("/assign").hasRole("MANAGER")
                .antMatchers("/employees/**").hasAnyRole("MANAGER", "TECHLEAD")
                .antMatchers("/api/employees/**").hasAnyRole("DEV", "TEST", "DEVOPS")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("manager").password("password").roles("MANAGER").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("techlead").password("password").roles("TECHLEAD").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("dev").password("password").roles("DEV").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("test").password("password").roles("TEST").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("devops").password("password").roles("DEVOPS").build());
        return manager;
    }
}
