package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
        http.csrf(csrf -> csrf.disable())
                .formLogin(formLogin->formLogin.permitAll())
                .authorizeRequests()
                .anyRequest().permitAll();

                // 특정 API에 대해 모든 사용자에게 접근 허용
//                .requestMatchers("/").permitAll()
//                .requestMatchers("/login").permitAll()
//                .requestMatchers("/save").permitAll()
//                .requestMatchers("/update").permitAll()
//                .requestMatchers("/main").permitAll()
//                .requestMatchers("/error").permitAll();
        //http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
