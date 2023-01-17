<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ include file="top.jsp"%>
<div class="container">
	<main>
		<div class="py-5 text-left">
		</div>

		<div class="row g-5">
			<div class="col-12 order-md-last">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-primary">장바구니</span> <span class="badge bg-primary rounded-pill">${fn:length(cart)}</span>
				</h4>
				<ul class="list-group mb-3">
					<c:forEach var="pdto" items="${cart}">
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">${pdto.prod_name}</h6>
								<small class="text-muted">${pdto.prod_amount}개</small>
							</div> <span class="text-muted">${pdto.prod_price}원</span>
						</li>
						<c:set var="totalPrice" value="${totalPrice + pdto.prod_price*pdto.prod_amount}"/>
					</c:forEach>
					<li class="list-group-item d-flex justify-content-between"><span>총합 (KRW))</span> <strong>${totalPrice}원</strong></li>
				</ul>

			</div>
		</div>
		<button type="button" onclick="location.href='/payment'" class="w-100 btn btn-lg btn-light">결제하기</button>
	</main>
</div>
