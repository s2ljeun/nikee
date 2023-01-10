<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<script>
function confirmDel(){
	if(confirm('회원을 삭제하시겠습니까?')){
		document.getElementById('delete_form').submit();
	}
}

function goEdit(){
	document.getElementById('edit_form').submit();
}
</script>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">회원</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<select class="form-select" aria-label="Default select example">
				<option value="member-no" selected>회원번호</option>
				<option value="id">아이디</option>
				<option value="name">이름</option>
				<option value="email">이메일</option>
				<option value="regdate">가입일</option>
				<option value="kakao">카카오</option>
				<option value="times">구매횟수</option>
				<option value="amount">구매금액</option>
			</select>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr align="center">
					<th scope="col">회원번호</th>
					<th scope="col">아이디</th>
					<th scope="col">이름</th>
					<th scope="col">이메일</th>
					<th scope="col">가입일</th>
					<th scope="col">카카오</th>
					<th scope="col">수정</th>
					<th scope="col">삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="mdto" items="${allMemberList}">
					<tr align="center">
						<td>${mdto.mem_no}</td>
						<td>${mdto.mem_id}</td>
						<td>${mdto.mem_name}</td>
						<td>${mdto.mem_email}</td>
						<td>${mdto.mem_regdate}</td>
						<td>
							<c:choose>
								<c:when test="${mdto.kakao eq 1}">
									Y
								</c:when>
								<c:otherwise>
									&nbsp;
								</c:otherwise>
							</c:choose></td>
						<td>
							<a onclick="location.href='/members/edit/${mdto.mem_no}'">수정</a>
						</td>
						<td>
							<form id="delete_form" action="/members/delete/${mdto.mem_no}" method="POST">
								<a onclick="confirmDel()">삭제</a>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>
</body>
</html>