<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="top.jsp"%>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
<form name="f" action="/members/update" method="POST">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">회원정보 수정</h1>
	</div>
	<div class="table-responsive">
		<table class="table table-sm">
			<tr>
				<th scope="col">회원번호</th>
				<td><input name="mem_no" value="${mdto.mem_no}" readonly/></td>
			</tr>
			<tr>
				<th scope="col">아이디</th>
				<td><input name="mem_id" value="${mdto.mem_id}" /></td>
			</tr>
			<tr>
				<th scope="col">이름</th>
				<td><input name="mem_name" value="${mdto.mem_name}" /></td>
			</tr>
			<tr>
				<th scope="col">이메일</th>
				<td><input name="mem_email" value="${mdto.mem_email}" readonly/></td>
			</tr>
			<tr>
				<th scope="col">가입일</th>
				<td><input name="mem_regdate" value="${mdto.mem_regdate}" readonly/></td>
			</tr>
			<tr>
				<th scope="col">카카오</th>
				<td><c:choose>
						<c:when test="${mdto.kakao eq 1}">
							<input name="kakao" value="1" readonly/>
						</c:when>
						<c:otherwise>
							<input name="kakao" value="0" readonly/>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</div>
	<div class="btn-group me-2">
		<button type="submit" class="btn btn-sm btn-outline-secondary">수정</button>
		<button onclick="location.href='/members'" type="button" class="btn btn-sm btn-outline-secondary">돌아가기</button>
	</div>
</form>
</main>
</body>
</html>