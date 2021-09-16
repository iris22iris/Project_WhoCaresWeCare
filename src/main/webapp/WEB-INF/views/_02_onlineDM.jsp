<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
   
    <title>線上DM瀏覽</title>
    <link rel="stylesheet" href="<c:url value='/css/_02onlinedm.css' />"
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
   <!-- jquery cnd -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
	
		<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
	</script>
	
</head>

<body>
	<div id="body">
    <!-- midlecontent start -->
    <div id="content" class="mainContent">
        
         <!-- side-menu start -->
        <nav class="side-menu col-2 ">
            <div class="sideSpace"></div>
            <div class="button d-flex justify-content-center align-items-center ">
                <a href="<c:url value=''/>" class="sideBtn">最新消息</a>
            </div>
            <div class="button d-flex justify-content-center align-items-center ">
                <a href="<c:url value=''/>"class="sideBtn">線上DM</a>
            </div>

            <!-- 各月份DM start -->
            <div class="accordion accordion-flush dmDate" id="accordionFlushExample ">
                <div class="accordion-item">
                 <c:forEach var="dms" items="${dms}" varStatus="vs">
                  <h2 class="accordion-header" id="flush-headingOne">
                    <button class="accordion-button collapsed" 
                        type="button" data-bs-toggle="collapse" 
                        data-bs-target="#flush-collapseOne" 
                        aria-expanded="false" aria-controls="flush-collapseOne">
                      <span>${dms.dmdate}</span>
                    </button>
                  </h2>
                  <div id="flush-collapseOne" class="accordion-collapse collapse" 
                    aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">
                        <a href="<c:url value='/dmMenu/${dms.dmId}' />">${dms.dmName}</a>
                        <br>
                    </div>
                  </div>
                </c:forEach>
                </div>
                </div>
            <!-- 各月份DM End -->
        </nav>
        <!-- side-menu end -->

        <!-- dmcontainer start -->
        <div class="dmcontainer col-10 ">
            
            <!-- title start -->
            <div class="title">
                <h1>  
                <i class="fas fa-th-large px-3"></i>
                DM瀏覽
                </h1>
            </div>
            <!-- title end -->
            <!-- dmImage start -->
            
            <div id="image" class="dmImage pt-3 ">
                 <img id="myimage" src="<c:url value='/images/DM/DM3.jpg' />"  alt="dm">
            	<c:forEach var='dm' items='${dmo}'>  
                    <img id="myimage" src="<c:url value='/images/DM/DM${dm.dmId}.jpg' />"  alt="dm">
				</c:forEach>
            </div>
            <!-- dmImage end -->
            <!-- fileBtnContainer start -->
<!--             <div class="filebuttoncontainer d-flex p-2 "> -->
<!--                 <div class="col-5 d-flex justify-content-end"> -->
<!--                     <button type="submit" class="btn-warning downloadBtn" >  -->
<!--                     DM檔案下載 -->
<!--                     </button> -->
<!--                 </div> -->
<!--         </div> -->

<!--                 <div class="backnext col-7 d-flex align-items-center p-2"> -->
<!--                     <i class="fas fa-chevron-circle-left"></i> -->
<!--                     <div>目前頁次/總頁次</div> -->
<!--                     <i class="fas fa-chevron-circle-right"></i> -->
<!--                 </div> -->
<!--             </div> -->
            <!-- fileBtnContainer end -->

        <!-- dmcontainer end -->


    </div>
     <!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
    
   </div>
    
    
   
 <script type="text/javascript" src="./js/_02onlinedm.js"></script> 
</body>
</html>