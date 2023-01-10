package com.nikeedev.nikee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
	private int mem_no;
	private String mem_id;
	private String mem_passwd;
	private String mem_name;
	private String mem_email;
	private String mem_regdate;
	private int kakao;
	private String mem_role;
}
