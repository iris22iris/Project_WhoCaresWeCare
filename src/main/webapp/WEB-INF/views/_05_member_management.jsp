<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='./css/_05member_management.css' />" type="text/css">
<!-- bootstrap -->
<link
	href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
	rel="stylesheet">
<!-- icon -->
<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<script>
	function memberProfile(obj){
		obj.href="/Whocares/_05_memberProfile/"+'<%=session.getAttribute("LoginOK")%>';
	}

	function favoriteList(obj){
		obj.href="/Whocares/_04_favoriteList/"+'<%=session.getAttribute("LoginOK")%>';
	}

	function rentOrderQuery(obj){
		obj.href="<c:url value='/rentOrderQuery/' />"+'<%=session.getAttribute("LoginOK")%>';
	}
	
	function orderQuery(obj){
		obj.href="<c:url value='/orderQuery/' />"+'<%=session.getAttribute("LoginOK")%>';
	}
	
	function reservationQuery(obj){
		obj.href="<c:url value='/reservationQuery/' />"+'<%=session.getAttribute("LoginOK")%>';
	}
	function qaQuery(obj){
		obj.href="<c:url value='/_06_problemReply/' />"+'<%=session.getAttribute("LoginOK")%>';
	}
	function contacUs(obj){
		obj.href="/Whocares/_02_contactUs/"+'<%=session.getAttribute("LoginOK")%>';
	}
	
</script>
<title>會員專區</title>

</head>
<body>
	<!-- main start -->
	<div id="body">
		<div id="content">
			<!-- 引入共同的頁首 -->
			<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

			<div
				class="main col-12 m-5 d-flex justify-content-center  p-3 rounded">

				<!-- member profile start -->
				<div class="row ">

					<div class="col-12  col-sm-12 col-md-4">
						<div class="col-12 ">
							<div
								class="title border rounded-circle d-flex justify-content-center align-items-center ">
								<h1>會員資料</h1>
							</div>
						</div>

						<div class="col-12 ">
							<div class="mt-5">
								<h1>
									<a style="text-decoration: none; color: black; cursor:pointer;"
										class="fas fa-caret-right" onclick="memberProfile(this)"><i>會員資料修改</i></a>
								</h1>
							</div>
							<div class="mt-5">
								<h1>
									<a style="text-decoration: none; color: black; cursor:pointer;"
										class="fas fa-caret-right" onclick="favoriteList(this)"><i>商品追蹤清單</i></a>
								</h1>
							</div>
							<div class="mt-5">
								<h1>
									<i class="fas fa-caret-right"></i>修改密碼
								</h1>
							</div>
						</div>
					</div>
					<!-- member profile end -->

					<!-- order status start -->
					<div
						class="
                col-12 col-md-4 col-sm-12 bcglightgray text-dark p-3 rounded">
						<div class="col-12 bcglightgray">
							<div
								class="title border border rounded-circle d-flex justify-content-center align-items-center ">
								<h1>訂單查詢</h1>
							</div>
						</div>

						<div class="col-12 bcglightgray">
							<div class="mt-5 ">
								<h1>
									<a style="text-decoration: none; color: black; cursor:pointer;"
										class="fas fa-caret-right" onclick="rentOrderQuery(this)"><i>租賃記錄查詢</i></a>
								</h1>
							</div>
							<div class="mt-5 ">
								<h1>
									<a style="text-decoration: none; color: black; cursor:pointer;"
										class="fas fa-caret-right" onclick="orderQuery(this)"><i>購買記錄查詢</i></a>
								</h1>
							</div>
							<div class="mt-5 ">
								<h1>
									<a style="text-decoration: none; color: black; cursor:pointer;"
										class="fas fa-caret-right" onclick="reservationQuery(this)"><i>預約記錄查詢</i></a>
								</h1>
							</div>

						</div>
					</div>
					<!-- order status end -->

					<!-- customer service reply start -->
					<div
						class="
                col-12 col-md-4 col-sm-12  p-3 rounded">
						<div class="col-12 ">
							<div
								class="title  border rounded-circle d-flex justify-content-center align-items-center ">
								<h1>客服回覆</h1>
							</div>
						</div>

						<div class="col-12 ">
							<div class="mt-5">
								<h1>
								<a style="text-decoration: none; color: black;"
									class="fas fa-caret-right" onclick="qaQuery(this)"><i>客服紀錄查詢</i></a>
								</h1>
								<div class="mt-5 ">
									<h1>
										<a style="text-decoration: none; color: black;"
											class="fas fa-caret-right" onclick="contacUs(this)" ><i>聯絡我們</i></a>
									</h1>
								</div>
							</div>
						</div>
					</div>
					<!-- customer service reply end -->

				</div>
			</div>
			<!-- 引入共同的頁尾 -->
			<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

		</div>
	</div>
	<!-- main end -->

	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>