package com.electioncomission.ec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;


    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserService());
        daoAuthenticationProvider.setPasswordEncoder(this.getPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(this.getAuthenticationProvider());

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests().antMatchers("/login","/otp").permitAll()
////                .and()
////                .authorizeRequests().anyRequest().hasAnyRole("BLO")
//                .and()
//                .formLogin().loginPage("/login")
//                .and()
//                .csrf().disable();


                http
                .authorizeRequests().antMatchers("/login").permitAll()
                        .antMatchers("/blo/**").hasAnyAuthority("BLO")
                        .antMatchers("/ro/**").hasAnyAuthority("RO")
//                        .antMatchers("/**").permitAll()
//                                        .anyRequest().authenticated()
//                        .antMatchers("/ceo/**").hasRole("CEO")
//                        .antMatchers("/deo/**").hasRole("DEO")


                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("mobileNumber")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                        .permitAll()
                        .and()
                        .logout()
                        .logoutSuccessUrl("/login")
                        .permitAll()
                        .and()
                        .csrf().disable();
    }
    @Bean
    public CustomUserDetailsServiceImpl getUserService() {
        return new CustomUserDetailsServiceImpl();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    private BeforeAuthenticationFilter getCustomFilter()
    {
        BeforeAuthenticationFilter beforeAuthenticationFilter = new BeforeAuthenticationFilter();
        try {
            beforeAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        }
        catch (Exception e)
        {

        }
        return beforeAuthenticationFilter;
    }

}
