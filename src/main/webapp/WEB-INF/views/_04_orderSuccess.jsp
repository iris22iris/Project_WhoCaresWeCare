<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value='' />"
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
    <!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
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
  		<i class="fas fa-shopping-cart pe-2"></i>謝謝您的訂購
	</div>
	<!-- title end! -->

	您的訂單已成立，請前往付款：
	<form method="POST" action="${pageContext.request.contextPath}/payPayment">
	<input type="text" name="custId" value="${OrdBean.customerBean.custId}" hidden="true"/>
	<input type="number" name="ordTotal"  step="1" value="${OrdBean.ordTotal}"/>
	<button type="submit" id="checkout">付款</button>
	</form>

	</div>
	

</div>
  
 <!-- bootstrap -->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>   

</body>
</html>