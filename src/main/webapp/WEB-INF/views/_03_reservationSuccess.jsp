<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 數字格式化標籤 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 計算文字處理標籤 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
		
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/_03rentProduct.css' />">
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">

<title>預約成功通知 </title>

<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jquery cnd -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<!-- 圖片切換 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
	<!-- 頁面顯示30秒跳轉至首頁 start -->
 <script type="text/javascript">  
	function countDown(){
		//30秒之後跳轉至首頁
		setTimeout("location.href='${pageContext.request.contextPath}/index'",30000);
                
	}
	//執行跳轉頁面函式
	countDown();
</script> 
<!-- 頁面顯示十秒跳轉至首頁 end -->
	<div id="body">
		<div id="content">
		<!-- header start -->
		<!-- 引入共同的頁首 -->
		<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
		<!-- header end -->

		<!-- main start -->
		<div class="container-fluid d-flex">
		
		<!-- 預約完成結果畫面 start -->
    
        <div class="modal-dialog ">
          <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title2 p-3 col-12 d-flex justify-content-evenly  " id="exampleModalLabel">成功預約通知</h1>
                
                </div>
                <div class="col-12 justify-content-evenly p-1  d-flex align-items-center ">
                    <h3 class="modal-title p-3 col-12 d-flex justify-content-evenly " id="exampleModalLabel">恭喜您成功加入預約候補!!!</h3> 
                </div>
                <div class="col-12 justify-content-evenly p-1  d-flex align-items-center row">
                    <label style=font-size:22px; for="inputAccount" class="form-label col-6">申請人:</label>
                    <label style=font-size:22px;  for="inputAccount" class="form-label col-6">${customerinfo[0].custName}</label>
                </div>
                <!-- 計算預約筆數 start -->
                <c:set value="${fn:length(myreservations)}" var="reservationlenth"/>
                <!-- 計算預約筆數 end -->
                <div class="col-12 justify-content-evenly p-1  d-flex align-items-center row">
                    <label style=font-size:22px; for="inputAccount" class="form-label col-6">您的預約序號為:</label>
                    <label style=font-size:22px;  for="inputAccount" class="form-label col-6 ">${myreservations[reservationlenth-1].waitNum}</label>
                </div>
                <div class="col-12 justify-content-evenly p-1  d-flex align-items-center row">
                    <label style=font-size:18px; for="inputAccount" class="form-label col-6">您的預約編號為:</label>                                                    
                                        
                    <label style=font-size:18px; for="inputAccount" class="form-label col-6"><fmt:formatNumber 
                    value="${myreservations[reservationlenth-1].reservationId}" pattern="00000000"/></label>                                    
                </div>
              
                <div class="col-12 justify-content-evenly p-1  d-flex align-items-center">
                    當商品到貨時,請於收到通知信48小時內完成預約
                </div>
                <div class="col-12 justify-content-evenly p-1  d-flex align-items-center">
                	若逾期需重新預約候補
                </div>
          </div>
        </div>
      
		<!-- 預約完成結果畫面 end -->
		
		
		
		</div>
		<!-- main end -->
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
		</div>
	<script type="text/javascript" src="./js/_05loginPopup3.js"></script>
	</div>
</body>

</html>