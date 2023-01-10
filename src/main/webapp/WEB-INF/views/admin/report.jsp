<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<script src="resources/js/datepicker.js"></script>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">리포트</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<input type="text" name="sdate" id="sdate" value="2023-01-09">
			<input type="text" name="edate" id="edate" value="2023-01-09">
			<div class="btn-group me-2">
				<button type="button" class="btn btn-sm btn-outline-secondary">저장하기</button>
			</div>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr align="center">
					<th scope="col">주문번호</th>
					<th scope="col">판매일</th>
					<th scope="col">상품번호</th>
					<th scope="col">상품명</th>
					<th scope="col">원가</th>
					<th scope="col">판매가</th>
					<th scope="col">수익</th>
				</tr>
			</thead>
			<tbody>
				<tr align="center">
					<td>1,001</td>
					<td>2023-01-09</td>
					<td>101</td>
					<td>에어포스 1'07</td>
					<td>50,000</td>
					<td>130,000</td>
					<td>80,000</td>
				</tr>
				<tr align="center">
					<td>1,002</td>
					<td>2023-01-07</td>
					<td>102</td>
					<td>코트 비전 알타</td>
					<td>70,000</td>
					<td>185,000</td>
					<td>115,000</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr align="center" style="background: rgb(33, 37, 41); color: white">
					<th scope="col">판매건수</th>
					<th scope="col">최다판매일</th>
					<th scope="col">최다판매품목</th>
					<th scope="col">원가총합</th>
					<th scope="col">판매가총합</th>
					<th scope="col">수익총합</th>
				</tr>
			</thead>
			<tbody>
				<tr align="center">
					<td>1,001</td>
					<td>2023-01-09</td>
					<td>에어포스 1'07</td>
					<td>120,000</td>
					<td>315,000</td>
					<td>35,000</td>
				</tr>
			</tbody>
		</table>
	</div>
</main>
</body>
</html>