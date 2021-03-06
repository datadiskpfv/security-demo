package uk.co.datadisk.securitydemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private UserDetailsServiceImpl userDetailsService;

    private DatadiskAuthenticationProvider datadiskAuthenticationProvider;

    public WebSecurityConfig(DatadiskAuthenticationProvider datadiskAuthenticationProvider) {
        this.datadiskAuthenticationProvider = datadiskAuthenticationProvider;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(datadiskAuthenticationProvider)
                .authorizeRequests()
                    .antMatchers("/resources/**", "/registration", "/h2-console/**", "/webjars/**", "/login", "/home", "/css/**").permitAll()
                    //.antMatchers("/admin/**").hasAuthority("ADMIN")
                    //.antMatchers("/shopping/**").hasAuthority("SHOPPING")
                    .antMatchers("/welcome").hasAuthority("USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .headers()
                    .frameOptions()
                    .disable()
                    .and()
                .csrf()
                    .disable();

    }

    //@Autowired
    //public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
   // }
}