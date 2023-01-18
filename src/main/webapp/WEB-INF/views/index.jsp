<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main>

	<div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-label="Slide 1" aria-current="true"></button>
			<button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2" class=""></button>
			<button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3" class=""></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="/resources/img/carousel1.jpg" />
				<div class="container">
					<div class="carousel-caption text-start">
						<h1>FEEL YOUR ALL</h1>
						<p>선택과 힘과 자유를 향해 움직이는 모든 순간을 위한 컬렉션. 몸과 마음이 아름답게 조화를 이루는 그 순간의 기분을 느껴보세요.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#">구매하기</a>
						</p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<img src="/resources/img/carousel2.jpg" />
				<div class="container">
					<div class="carousel-caption">
						<h1>나이크 젠비 레깅스</h1>
						<p>다리를 숨쉬게 하는 편안함을 만나보세요.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#">구매하기</a>
						</p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<img src="/resources/img/carousel3.jpg" />
				<div class="container">
					<div class="carousel-caption text-end">
						<h1>우수한 그립력. 뛰어난 정확성.</h1>
						<p>그립니트를 통해 구현되는 팬텀 GX의 정확성을 경험해 보세요.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#">Browse gallery</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
		</button>
	</div>


	<!-- Marketing messaging and featurettes
  ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->

	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<c:forEach var="pdto" items="${allProdList}">
				<div class="col-lg-4">
					<a href="/products/${pdto.prod_no}"> <img src="resources/img/${pdto.prod_img}" height="250" width="250" />
					</a> <a href="/products/${pdto.prod_no}">
						<h5 class="fw-normal">${pdto.prod_name}</h5>
					</a>
				</div>
			</c:forEach>
		</div>
		<!-- /.row -->


		<hr class="featurette-divider">

		<!-- /END THE FEATURETTES -->

	</div>
	<!-- /.container -->


	<%@ include file="bottom.jsp"%>