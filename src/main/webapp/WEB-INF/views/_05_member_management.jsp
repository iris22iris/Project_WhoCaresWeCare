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
	window.onload = function() {
		searchBox();
	}
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

			<!-- conatiner start -->
			<div class="container main-div">
				<!-- member profile start -->
				<div class="row block">
					<div class="col-12 title">
						<div class="circleTitle rounded-circle" id="memberProfile">
							會員資料
						</div>
					</div>
					<div class="col-12 mt-4">
						<div class="contentLink">
							<a onclick="memberProfile(this)">
								會員資料修改</a>
						</div>
						<div class="contentLink">
							<a onclick="favoriteList(this)">
								商品追蹤清單</a>
						</div>
						<div class="contentLink">
							<a>修改密碼</a>
						</div>
					</div>
				</div>
				<!-- member profile end -->

				<!-- orderQuery start -->
				<div class="row block">
					<div class="col-12 title">
						<div class="circleTitle rounded-circle" id="orderQuery">
							訂單查詢
						</div>
					</div>
					<div class="col-12 mt-4">
						<div class="contentLink">
							<a onclick="rentOrderQuery(this)">
								租賃記錄查詢</a>
						</div>
						<div class="contentLink">
							<a  onclick="orderQuery(this)">
								購買記錄查詢</a>
						</div>
						<div class="contentLink">
							<a onclick="reservationQuery(this)">
								預約記錄查詢</a>
						</div>
					</div>
				</div>
				<!-- orderQuery end -->

				<!-- customer service start -->
				<div class="row block">
					<div class="col-12 title">
						<div class="circleTitle rounded-circle" id="customerService">
							客戶服務
						</div>
					</div>

					<div class="col-12 mt-4">
						<div class="contentLink">
							<a onclick="qaQuery(this)">
								客服紀錄查詢</a>
						</div>
						<div class="contentLink">
							<a onclick="contacUs(this)" >
							聯絡我們</a>
						</div>
					</div>
				</div>
				<!-- customer service  end -->
			</div>
			<!-- conatiner end -->

		</div>
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
	
	</div>
	<!-- main end -->

	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>