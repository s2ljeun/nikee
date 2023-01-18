<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<script src="resources/js/datepicker.js"></script>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<form name="f" action="/mypage/update" method="POST">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">내 정보</h1>
	</div>
	<div class="table-responsive">
		<input type="hidden" id="mem_no" name="mem_no" value="${mdto.mem_no}" readonly/>
		<table class="table table-sm">
			<tr>
				<th scope="col">아이디</th>
				<td><input id="mem_id" name="mem_id" value="${mdto.mem_id}" readonly/></td>
			</tr>
			<tr>
				<th scope="col">이름</th>
				<td><input id="mem_name" name="mem_name" value="${mdto.mem_name}" /></td>
			</tr>
			<tr>
				<th scope="col">이메일</th>
				<td><input id="mem_email" name="mem_email" value="${mdto.mem_email}" readonly /></td>
			</tr>
		</table>
	</div>
	<div class="btn-group me-2">
		<button type="submit" class="btn btn-sm btn-outline-secondary">수정</button>
	</div>
	</form>
</main>
</body>
</html>