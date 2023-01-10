package com.nikeedev.nikee.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration // 스프링 환경 세팅을 돕는 어노테이션
@EnableWebSecurity // 스프링 시큐리티 설정할 클래스라고 알려주는 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    LoginIdPwValidator loginIdPwValidator;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated() // 어떤 URI로 접근하던 인증이 필요함
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginProc") // 이 URI 호출시 스프린 시큐리티로 폼 정보를 제출 / form의 action
                    .usernameParameter("id") // 폼 input name값: default - username
                    .passwordParameter("passwd") // 폼 input name값: default - password
                    .defaultSuccessUrl("/", true) //로그인 성공시 이동할 URI: default - "/"
                    .permitAll()
                .and()
                    .logout()
        			.logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc")); // 이 URI 호출시 로그아웃
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	//인증 예외 추가
        web.ignoring().antMatchers("/resources/js/**", "/resources/css/**", "/resources/img/**");
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginIdPwValidator);
    }
}