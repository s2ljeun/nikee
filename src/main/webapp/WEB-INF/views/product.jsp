<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main>
<div class="container">
	<div class="row mb-2" style="margin:100px 0px">
		<div class="col-md-6" style="text-align:right">
			<img src="/resources/img/${pdto.prod_img}" />
		</div>
		<div class="col-md-6">
			<form name="f" id="prodForm" method="POST" action="/cart">
				<input type="hidden" name="prod_no" value="${pdto.prod_no}" />
				<table>
					<tr>
						<td><h2>${pdto.prod_name}</h2></td>
					</tr>
					<tr>
						<th>가격</th>
						<td>${pdto.prod_price}\</td>
					</tr>
					<tr>
						<th>수량</th>
						<td><input id="prod_amount" name="prod_amount" value="1" type="number" /></td>
					</tr>
				</table>
			<button type="button" onClick="goCart()" class="w-100 btn btn-lg btn-light">장바구니에 담기</button>
			</form>
		</div>
	</div>
</div>
</main>
<script>
	function goCart(){
		if($("#prod_amount").val() == ""){
			alert("상품갯수를 정해주세요.");
			return
		}
		
		$("#prodForm").submit();
	}
</script>

</body>
</html>