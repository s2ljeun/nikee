<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<script src="resources/js/datepicker.js"></script>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<div class="chartjs-size-monitor">
		<div class="chartjs-size-monitor-expand">
			<div class=""></div>
		</div>
		<div class="chartjs-size-monitor-shrink">
			<div class=""></div>
		</div>
	</div>
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">대시보드</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<input type="text" name="sdate" id="sdate" value="2023-01-09"> <input type="text" name="edate" id="edate" value="2023-01-09">
		</div>
	</div>

	<canvas class="my-4 w-100 chartjs-render-monitor" id="myChart" width="1538" height="649" style="display: block; width: 1538px; height: 649px;"></canvas>

</main>
</body>
</html>