<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value='/css/_04orderSucess.css' />"
    type="text/css">
    <link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">
	   <!-- bootstrap -->
    <link
    href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
    rel="stylesheet">
    <!-- icon -->
    <link rel="stylesheet"
    href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
<title>訂購完成</title>
<script>
	window.onload = function() {
		searchBox();
	}
</script>
</head>
<body>
<div id="body">
	<div id="content">
	 <!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

	<!-- title start! -->
	<div class="title container">
		訂單已確認，謝謝您的訂購，請點選前往付款完成訂單。
	</div>
	<!-- title end! -->
	<div class="payFrom">
	<form method="POST" action="${pageContext.request.contextPath}/payPayment">
	<input type="text" name="custId" value="${OrdBean.customerBean.custId}" hidden="true"/>
	<input type="number" name="ordTotal"  step="1" value="${OrdBean.ordTotal}" hidden="true"/>
	<button class="submitBtn" type="submit" id="checkout">線上刷卡</button>
	</form>
	</div>
	</div>
	
    <!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
</div>
  
 <!-- bootstrap -->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>   

</body>
</html>