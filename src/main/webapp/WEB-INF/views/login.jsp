<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<form method="post" action="/loginProc">
			<img class="mb-4" src="/docs/5.2/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
			<h1 class="h3 mb-3 fw-normal">로그인</h1>

			<div class="form-floating">
				<input name="id" type="text" class="form-control" id="floatingInput" placeholder="name@example.com">
					<label for="floatingInput">아이디</label>
			</div>
			<div class="form-floating">
				<input name="passwd" type="password" class="form-control" id="floatingPassword" placeholder="Password">
					<label for="floatingPassword">패스워드</label>
			</div>

			<div class="checkbox mb-3">
				<label> <input type="checkbox" value="remember-me"> 아이디 기억하기
				</label>
			</div>
			<!-- csrf 토큰 발행 -->
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<button class="w-100 btn btn-lg btn-primary mb-2" type="submit">로그인</button>
			<button class="w-100 btn btn-lg btn-light">회원가입</button>
			<p class="mt-5 mb-3 text-muted">© 2023</p>
		</form>
	</main>
</body>
</html>