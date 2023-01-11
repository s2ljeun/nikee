<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- Bootstrap & Jquery -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script src="resources/js/jquery-3.6.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<!-- login Style -->
<link href="resources/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
	<main class="form-signin w-100 m-auto">
		<form name="f" action="/join" method="POST">
			<a href="/index"> <img class="mb-4" src="/resources/icon/shop.svg" alt="" width="72" height="57">
			</a>
			<h1 class="h3 mb-3 fw-normal">회원가입</h1>

			<div class="form-floating">
				<input name="mem_id" type="text" class="form-control" id="floatingInput" placeholder="name@example.com"> <label for="floatingInput">아이디</label>
			</div>

			<div class="form-floating">
				<input name="mem_passwd" type="password" class="form-control" id="floatingPassword" placeholder="Password"> <label for="floatingPassword">비밀번호</label>
			</div>
			<div class="form-floating">
				<input onkeyup="passConfirm()" type="password" class="form-control" id="floatingPasswordChk" placeholder="Password"> <label for="floatingPasswordChk">비밀번호 확인</label>
			</div>
			<div id="confirmMsg"></div>
			<div class="form-floating">
				<input name="mem_name" type="text" class="form-control" id="floatingName" placeholder="홍길동"> <label for="floatingName">이름</label>
			</div>
			<div class="form-floating">
				<input name="mem_email" type="text" class="form-control" id="floatingEmail" placeholder="홍길동"> <label for="floatingEmail">이메일</label>
			</div>
			<button class="w-100 btn btn-lg btn-primary mb-2" type="submit">가입</button>
			<button onclick="location.href='/index'" class="w-100 btn btn-lg btn-light" type="button">돌아가기</button>
			</div>
		</form>
	</main>

	<script type="text/javascript">
		// 패스워드 일치 판정
		function passConfirm() {
			var passwd = document.getElementById('floatingPassword');
			var passwd_chk = document.getElementById('floatingPasswordChk');
			var confrimMsg = document.getElementById('confirmMsg');
			var correctColor = "blue";
			var wrongColor = "red";

			if (passwd.value == passwd_chk.value) {
				confirmMsg.style.color = correctColor;
				confirmMsg.innerHTML = "비밀번호 일치";
			} else {
				confirmMsg.style.color = wrongColor;
				confirmMsg.innerHTML = "비밀번호 불일치";
			}
		}
	</script>
</body>
</html>