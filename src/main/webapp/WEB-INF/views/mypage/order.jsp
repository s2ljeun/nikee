<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">주문</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<select class="form-select" aria-label="Default select example">
				<option value="order-no" selected>주문번호</option>
				<option value="date" selected>주문일</option>
				<option value="product-name">구매상품</option>
				<option value="order-amount">갯수</option>
				<option value="price">결제금액</option>
				<option value="status">상태</option>
			</select>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr align="center">
					<th scope="col">주문번호</th>
					<th scope="col">주문일</th>
					<th scope="col">구매상품</th>
					<th scope="col">결제금액</th>
					<th scope="col">상태</th>
					<th scope="col">취소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="odto" items="${orderList}">
					<tr align="center">
					<td>${odto.order_no}</td>
					<td>${odto.order_regdate}</td>
					<td>구매상품</td>
					<td>${odto.order_price}</td>
					<td>상태</td>
					<td>
						<a href="#">취소</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>
</body>
</html>