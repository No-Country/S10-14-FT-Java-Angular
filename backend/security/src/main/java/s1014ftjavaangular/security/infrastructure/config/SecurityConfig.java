package s1014ftjavaangular.security.infrastructure.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import s1014ftjavaangular.security.domain.enums.Rol;
import s1014ftjavaangular.security.infrastructure.security.jwt.JwtAuthorizationFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final UserDetailsService customAccountDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors().configurationSource(this.corsConfigurationSource());

        AuthenticationManagerBuilder auth = http.getSharedObject(
                AuthenticationManagerBuilder.class
        );

        auth.userDetailsService(customAccountDetailsService).passwordEncoder(passwordEncoder);

        http.authenticationManager(authenticationManager);
        //AuthenticationManager authenticationManager = auth.build();
        //http.authenticationManager(authenticationManager);

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,
                        "/v3/**",
                        "/swagger-ui/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/api/accounts/current-session"
                ).hasAnyRole(Rol.ADMIN.name(), Rol.CUSTOMER.name(), Rol.EMPLOYEE.name())
                .requestMatchers(HttpMethod.POST,
                        "/api/accounts/register",
                        "/api/accounts/login"
                ).permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users").hasAnyRole(Rol.ADMIN.name(), Rol.CUSTOMER.name(), Rol.EMPLOYEE.name())
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        //Seteo los metodos y origines permitidos en la API
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setMaxAge(3600l);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        log.info("configured cors: {}", source);
        return source;
    }
}
