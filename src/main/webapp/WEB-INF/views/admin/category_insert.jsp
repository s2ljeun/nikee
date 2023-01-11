<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="top.jsp"%>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
<form name="f" action="/category/insert" method="POST">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">카테고리 등록</h1>
	</div>
	<div class="table-responsive">
		<table class="table table-sm">
			<tr>
				<th scope="col">카테고리명</th>
				<td><input name="cate_name" /></td>
			</tr>
			
		</table>
	</div>
	<div class="btn-group me-2">
		<button type="submit" class="btn btn-sm btn-outline-secondary">확인</button>
		<button onclick="location.href='/category'" type="button" class="btn btn-sm btn-outline-secondary">돌아가기</button>
	</div>
</form>
</main>
</body>
</html>