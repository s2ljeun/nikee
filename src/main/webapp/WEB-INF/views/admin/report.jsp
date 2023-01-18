<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="top.jsp"%>
<script src="resources/js/datepicker.js"></script>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">리포트</h1>
		<!--
		<div class="btn-toolbar mb-2 mb-md-0">
			<input type="text" name="sdate" id="sdate" value="2023-01-09">
			<input type="text" name="edate" id="edate" value="2023-01-09">
			<div class="btn-group me-2">
				<button type="button" class="btn btn-sm btn-outline-secondary">저장하기</button>
			</div>
		</div>
		-->
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr align="center">
					<th scope="col">주문번호</th>
					<th scope="col">판매일</th>
					<th scope="col">상품명</th>
					<th scope="col">원가</th>
					<th scope="col">판매가</th>
					<th scope="col">수익</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="odto" items="${olist}">
					<tr align="center">
					<td>${odto.order_no}</td>
					<td>${odto.order_regdate}</td>
					<td>
					<c:choose>
				        <c:when test="${fn:length(odto.order_products) gt 12}">
					        <c:out value="${fn:substring(odto.order_products, 0, 11)}"/>...
				        </c:when>
				        <c:otherwise>
					        <c:out value="${odto.order_products}">
					        </c:out>
					    </c:otherwise>
					</c:choose>
					</td>
					<td>${odto.order_cost}</td>
					<td>${odto.order_price}</td>
					<td>${odto.order_income}</td>
				</tr>
				<c:set var="totalCost" value="${totalCost + odto.order_cost}"/>
				<c:set var="totalPrice" value="${totalPrice + odto.order_price}"/>
				<c:set var="totalIncome" value="${totalIncome + odto.order_income}"/>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr align="center" style="background: rgb(33, 37, 41); color: white">
					<th scope="col">판매건수</th>
					<th scope="col">원가총합</th>
					<th scope="col">판매가총합</th>
					<th scope="col">수익총합</th>
				</tr>
			</thead>
			<tbody>
				<tr align="center">
					<td>${fn:length(olist)}</td>
					<td>${totalCost}</td>
					<td>${totalPrice}</td>
					<td>${totalIncome}</td>
				</tr>
			</tbody>
		</table>
	</div>
</main>
</body>
</html>