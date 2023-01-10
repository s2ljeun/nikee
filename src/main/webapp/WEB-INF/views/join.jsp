<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
<form name="f" action="/join" method="POST">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">회원가입</h1>
	</div>
	<div class="table-responsive">
		<table class="table table-sm">
			<tr>
				<th scope="col">아이디</th>
				<td><input name="mem_id" /></td>
			</tr>
			<tr>
				<th scope="col">비밀번호</th>
				<td><input name="mem_passwd" /></td>
			</tr>
			<tr>
				<th scope="col">이름</th>
				<td><input name="mem_name" /></td>
			</tr>
			<tr>
				<th scope="col">이메일</th>
				<td><input name="mem_email" /></td>
			</tr>
		</table>
	</div>
	<div class="btn-group me-2">
		<button type="submit" class="btn btn-sm btn-outline-secondary">가입</button>
		<button onclick="location.href='/index'" type="button" class="btn btn-sm btn-outline-secondary">돌아가기</button>
	</div>
</form>
</main>
</body>
</html>