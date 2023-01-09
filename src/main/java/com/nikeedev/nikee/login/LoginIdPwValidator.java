package com.nikeedev.nikee.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nikeedev.nikee.dto.MemberDTO;
import com.nikeedev.nikee.service.MemberMapper;

@Service
public class LoginIdPwValidator implements UserDetailsService {
	@Autowired
    private MemberMapper memberMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	public UserDetails loadUserByUsername(String insertedId) throws UsernameNotFoundException {
		// 사용자가 입력한 id가 인자로 들어옴
		// 유저id로 DTO꺼내오기
		MemberDTO mdto = memberMapper.getMemberById(insertedId);
	        
	        if (mdto == null) {
	            return null;
	        }
	        
	        String passwd = mdto.getMem_passwd(); //"d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db"
	        String roles = mdto.getMem_role(); //"USER"

	        return User.builder()
	                .username(insertedId)
	                .password(passwd)
	                .roles(roles)
	                .build();
	}

}
