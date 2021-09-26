<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <title>完成訂購</title>

    <script>
	window.onload = function() {
		searchBox();
	}

    countDown();

    function countDown(){
		//30秒之後跳轉至首頁
		setTimeout("location.href='${pageContext.request.contextPath}/index'",5000);
                
	}
	</script>
    <style>
    .title{
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 30vh;
        font-family: mainfont-medium;
        color:rgb(58, 53, 50);
        text-shadow: 3px 3px 3px 3px silver;
        font-size: 40px;
    }
    </style>    
</head>
<body>
<div id="body">
	<div id="content">
	
     <!-- 引入共同的頁首 -->
     <jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
     
	<!-- title start! -->
	<div class="title container">
        <span>付款成功，我們會盡快為您準備訂單。</span>
	</div>
	<!-- title end! -->
	</div>
	<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

</div>
  
 <!-- bootstrap -->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>   

</body>
</html>