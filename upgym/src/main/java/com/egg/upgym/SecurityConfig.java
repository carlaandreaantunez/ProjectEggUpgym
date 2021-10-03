package com.egg.upgym;

import com.egg.upgym.servicio.GimnasioServicio;
import com.egg.upgym.servicio.UsuarioServicio;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServicio ususer;
    
    @Autowired
    private GimnasioServicio gimser;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(ususer)
                .passwordEncoder(encoder).and().userDetailsService(gimser).passwordEncoder(encoder);
    }

    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/css/**", "/imagenes/**", "/assets/**", "/js/**", "/vendor/**", "/usuarios/crear", "/login/elegir","/usuarios/guardar", "/gimnasios/crear", "/gimnasios/guardar", "/gimnasios", "/gimnasios/buscar/**","/reservas/horarios", "/gimnasios/staff").permitAll()
                    .antMatchers("/**").authenticated()
                .and()
                    .exceptionHandling().accessDeniedPage("/error-403")
                .and()
                .formLogin()
                
                    .loginPage("/login")
                        .loginProcessingUrl("/logincheck")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=error")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                        .deleteCookies("JSESSIONID")
                .and()
                    .csrf().disable();

    }
}
