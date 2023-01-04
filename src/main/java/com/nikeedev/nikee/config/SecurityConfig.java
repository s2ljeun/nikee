package com.nikeedev.nikee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //스프링 환경 세팅을 돕는 어노테이션
@EnableWebSecurity //스프링 시큐리티 설정할 클래스라고 알려주는 어노테이션
public class SecurityConfig {

  
  //Web Security Configure 위해 사용
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
      //인증 예외 추가해주기
      return (web) -> web.ignoring().antMatchers("/resources/js/**","/resources/css/**","/resources/img/**");
  }

  //Http Security Configure 위해 사용
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
      	.authorizeRequests()
      		.anyRequest().permitAll(); //어떤 URI로 접근하던 인증이 필요하지 않음
      	
      return http.build();
                  
  }
  
}

