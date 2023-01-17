<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ include file="top.jsp"%>
<div class="container">
	<main>
		<div class="py-5 text-left">
			<h2>결제</h2>
		</div>

		<div class="row g-5">
			<div class="col-md-5 col-lg-4 order-md-last">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-primary">장바구니</span> <span class="badge bg-primary rounded-pill">${fn:length(cart)}</span>
				</h4>
				<ul class="list-group mb-3">
					<c:forEach var="pdto" items="${cart}">
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">${pdto.prod_name}</h6>
								<small class="text-muted">${pdto.prod_amount}개</small>
							</div>
							<span class="text-muted">${pdto.prod_price}원</span>
						</li>
						<c:set var="totalPrice" value="${totalPrice + pdto.prod_price}"/>
					</c:forEach>
					
					<li class="list-group-item d-flex justify-content-between"><span>총합 (KRW))</span>
						<strong>${totalPrice}원</strong>
					</li>
				</ul>

			</div>
			<div class="col-md-7 col-lg-8">
				<h4 class="mb-3">주문자 정보</h4>
				<form method="POST" class="needs-validation" novalidate="" action="payment/proceed">
					<div class="row g-3">
						<input type="hidden" name="order_prod">
						
						<div class="col-12">
							<label for="order_name" class="form-label">이름</label>
							<input type="text" class="form-control" id="order_name" name="order_name" placeholder="" value="" required="">
						</div>
						<div class="col-12">
							<label for="order_addr" class="form-label">주소</label>
							<input type="text" class="form-control" id="order_addr" name="order_addr" placeholder="1234 Main St" required="">
						</div>
					</div>
					<hr class="my-4">

					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="same-address">
						<label class="form-check-label" for="same-address">회원 정보와 주문자 정보가 동일합니다.</label>
					</div>

					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="save-info">
						<label class="form-check-label" for="save-info">이 정보를 다음에도 사용</label>
					</div>

					<hr class="my-4">

					<button class="w-100 btn btn-primary btn-lg" type="submit">결제하기</button>
				</form>
			</div>
		</div>
	</main>
</div>
