<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="top.jsp"%>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
<form name="f" action="/members/edit" method="POST">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">상품 등록</h1>
	</div>
	<div class="table-responsive">
		<table class="table table-sm">
			<tr>
				<th scope="col">상품번호</th>
				<td><input name="prod_no" value="${pdto.prod_no}" readonly/></td>
			</tr>
			<tr>
				<th scope="col">카테고리</th>
				<td><input name="cate_no" value="${pdto.cate_no}" /></td>
			</tr>
			<tr>
				<th scope="col">상품명</th>
				<td><input name="prod_name" value="${pdto.prod_name}" /></td>
			</tr>
			<tr>
				<th scope="col">재고</th>
				<td><input name="prod_amount" value="${pdto.prod_amount}" readonly/></td>
			</tr>
			<tr>
				<th scope="col">원가</th>
				<td><input name="prod_cost" value="${pdto.prod_cost}" readonly/></td>
			</tr>
			<tr>
				<th scope="col">현재 판매가</th>
				<td><input name="prod_price" value="${pdto.prod_price}" /></td>
			</tr>
			<tr>
				<th scope="col">예상이익</th>
				<td><input name="prod_income" value="${pdto.prod_income}" /></td>
			</tr>
			<tr>
				<th scope="col">상태</th>
				<td><input name="prod_sale" value="${pdto.prod_sale}" /></td>
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