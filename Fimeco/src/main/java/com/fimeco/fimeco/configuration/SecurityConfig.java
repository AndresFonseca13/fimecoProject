package com.fimeco.fimeco.configuration;
import com.fimeco.fimeco.configuration.Filter.JwtTokenValidator;
import com.fimeco.fimeco.infra.services.UserDetailServiceImpl;
import com.fimeco.fimeco.infra.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Aquí se configuran las reglas de autorización para las solicitudes HTTP.
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    http.requestMatchers("/admin/**").hasAnyRole("ADMIN", "DEVELOPER");
                    http.requestMatchers("/client/**").hasAnyRole("DEVELOPER", "ADMIN", "CLIENT");
                    http.requestMatchers("/employee/**").hasAnyRole("WORKER", "ADMIN", "DEVELOPER");
                    http.requestMatchers("/product/**").hasAnyRole("ADMIN", "DEVELOPER","CLIENT");
                    http.requestMatchers("/supplier/**").hasAnyRole("ADMIN", "DEVELOPER","WORKER");
                    http.requestMatchers("/material/**").hasAnyRole("ADMIN", "DEVELOPER","WORKER");
                    http.requestMatchers("/order/**").hasAnyRole("ADMIN", "DEVELOPER","CLIENT");
                    http.requestMatchers(HttpMethod.GET, "method/get").hasAnyRole("GUEST");

                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
