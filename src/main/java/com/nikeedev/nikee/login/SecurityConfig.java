package com.nikeedev.nikee.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 스프링 환경 세팅을 돕는 어노테이션
@EnableWebSecurity // 스프링 시큐리티 설정할 클래스라고 알려주는 어노테이션
public class SecurityConfig {

	@Autowired
	LoginIdPwValidator loginIdPwValidator;

	// Web Security Configure 위해 사용
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		// 인증 예외 추가해주기
		return (web) -> web.ignoring().antMatchers("/resources/js/**", "/resources/css/**", "/resources/img/**");
	}

	// Http Security Configure 위해 사용
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //post매핑 활성화
			.authorizeRequests()
				.antMatchers("/", "/index**").permitAll() // 이 URI는 인증 필요 없음
				//.antMatchers("/admin**").hasAuthority("ROLE_ADMIN") // 유저 role에 따라 접근제어 / role은 db에 넣어둠
				//.anyRequest().authenticated() // 어떤 URI로 접근하던 인증이 필요함
				.anyRequest().permitAll() //어떤 URI로 접근하던 인증이 필요하지 않음
			.and()
				.csrf().disable() //post매핑 활성화
				.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/loginProc") // 스프링 시큐리티로 폼 정보 보낼 매핑 /
					.usernameParameter("id") // 폼 input name값: default - username
					.passwordParameter("passwd") // 폼 input name값: default - password
					.defaultSuccessUrl("/", true) // 로그인 성공시 이동할 URI: default - "/"
					.failureUrl("/login") //로그인 실패시 이동할 URI
					.permitAll()
			.and()
				.logout();
		return http.build();

	}
	
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//유저가 form을 발송하면 입력값이 loginIdPwValidator로 넘어가 db값과 비교 가능
        auth.userDetailsService(loginIdPwValidator);
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
