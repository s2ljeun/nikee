<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">카테고리</h1>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr align="center">
					<th scope="col">카테고리 번호</th>
					<th scope="col">카테고리명</th>
					<th scope="col">총 상품</th>
					<th scope="col">판매중</th>
					<th scope="col">일시품절</th>
					<th scope="col">판매중지</th>
					<th scope="col">수정</th>
					<th scope="col">삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cdto" items="${allCateList}">
					<tr align="center">
					<td>${cdto.cate_no}</td>
					<td>${cdto.cate_name}</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>
						<a href="#">수정</a>
					</td>
					<td>
						<a href="#">삭제</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<button onclick="location.href='/category/insert'" class="btn btn-sm btn-outline-secondary">카테고리 등록</button>
</main>
</body>
</html>