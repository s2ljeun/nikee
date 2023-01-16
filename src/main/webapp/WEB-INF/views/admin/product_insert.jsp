<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
<form id="prodForm" name="f" action="/admin/products/insert" method="POST" enctype="multipart/form-data">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">상품 등록</h1>
	</div>
	<div class="table-responsive">
		<table class="table table-sm">
			<tr>
				<th scope="col">카테고리</th>
				<td>
					<select name="cate_no">
						<c:forEach var="clist" items="${clist}">
							<option value="${clist.cate_no}">${clist.cate_name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="col">상품명</th>
				<td><input id="prod_name" name="prod_name" /></td>
			</tr>
			<tr>
				<th scope="col">재고</th>
				<td><input type="number" id="prod_amount" name="prod_amount" /></td>
			</tr>
			<tr>
				<th scope="col">원가</th>
				<td><input type="number" id="prod_cost" name="prod_cost" /></td>
			</tr>
			<tr>
				<th scope="col">판매가</th>
				<td><input type="number" id="prod_price" name="prod_price" /></td>
			</tr>
			<tr>
				<th scope="col">예상이익</th>
				<td><input type="number" id="prod_income" name="prod_income" value="1" readonly /></td>
			</tr>
			<tr>
				<th scope="col">상태</th>
				<td>
					<select name="prod_sale">
						<option value="0">일시품절</option>
						<option value="1">판매중</option>
						<option value="2">판매중지</option>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="col">대표이미지</th>
				<td>
					<input type="file" id="prod_img" name="prod_img">
				</td>
			</tr>
			
		</table>
	</div>
	<div class="btn-group me-2">
		<button onclick="submitForm()" type="button" class="btn btn-sm btn-outline-secondary">등록</button>
		<button onclick="location.href='/admin/products'" type="button" class="btn btn-sm btn-outline-secondary">돌아가기</button>
	</div>
</form>
</main>

<script>
	function submitForm(){
		var name = $("#prod_name").val();
		var amount = $("#prod_amount").val();
		var cost = $("#prod_cost").val();
		var price = $("#prod_price").val();
		var img = $("#prod_img").val();
		
		if(name == ""){
			alert("상품명을 입력해주세요.");
			$("#prod_name").focus();
			return
		}else if(amount == ""){
			alert("재고를 입력해주세요.");
			$("#prod_amount").focus();
			return
		}else if(cost == ""){
			alert("원가를 입력해주세요.");
			$("#prod_cost").focus();
			return
		}else if(price == ""){
			alert("판매가를 입력해주세요.");
			$("#prod_price").focus();
			return
		}else if(img == ""){
			alert("대표이미지를 설정해주세요.");
			$("#prod_img").focus();
			return
		}
		
		$("#prodForm").submit();
	}
</script>

</body>
</html>