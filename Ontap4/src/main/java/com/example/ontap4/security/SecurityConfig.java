package com.example.ontap4.security;

import com.example.ontap4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenProvider customAuthenProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests()
                //hasAuthority() người dùng sẽ chỉ được phép truy cập nếu họ được cấp quyền READ
                .antMatchers(HttpMethod.GET, "/book").hasAuthority(Authority.READ)
                .antMatchers(HttpMethod.GET, "/book/add").hasAuthority(Authority.CREATE)
                .antMatchers(HttpMethod.POST, "/book/save").hasAuthority(Authority.CREATE)
                .antMatchers(HttpMethod.GET, "/book/{\\d+}").hasAuthority(Authority.READ)
                .antMatchers(HttpMethod.GET, "/book/edit/{\\d+}").hasAuthority(Authority.EDIT)
                .antMatchers(HttpMethod.GET, "/book/delete/{\\d+}").hasAuthority(Authority.DELETE)
                .antMatchers(HttpMethod.GET, "/book/search").hasAuthority(Authority.SEARCH)
                .antMatchers(HttpMethod.POST, "/book/search").hasAuthority(Authority.SEARCH)
                .antMatchers("/**").permitAll();
    }

    public static PasswordEncoder delegatePasswordEncoder(String encodingType) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        return new DelegatingPasswordEncoder(encodingType, encoders);
    }

    @Bean
    public PasswordEncoder encoder() {
        return SecurityConfig.delegatePasswordEncoder("bcrypt");
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        Collection<UserDetails> users = new ArrayList<>();
        User trung = new User("trung", encoder().encode("123"));
        trung.setAuthority(Authority.READ, Authority.CREATE);
        User trung1 = new User("trung1", encoder().encode("123"));
        trung1.setAuthority(Authority.READ, Authority.SEARCH);
        User trung2 = new User("trung2", encoder().encode("123"));
        trung2.setAuthority(Authority.READ, Authority.DELETE);
        User trung3 = new User("trung3", encoder().encode("123"));
        trung3.setAuthority(Authority.READ, Authority.CREATE, Authority.EDIT);

        users.add(trung);
        users.add(trung1);
        users.add(trung2);
        users.add(trung3);
        return new InMemoryUserDetailsManager(users);
    }

}
