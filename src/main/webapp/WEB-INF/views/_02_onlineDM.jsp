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

<body id="body">    
    <!-- midlecontent start -->
    <div class="midlecontent d-flex ">
        
         <!-- side-menu start -->
        <nav class="side-menu col-2 ">
            <div class="sideSpace"></div>
            <div class="button d-flex justify-content-center align-items-center ">
                <a href="<c:url value=''/>" class="sideBtn">最新消息</a>
            </div>
            <div class="button d-flex justify-content-center align-items-center ">
                <a href="<c:url value=''/>"class="sideBtn">線上DM</a>
            </div>
            <div></div>
        </nav>
        <!-- side-menu end -->

        <!-- dmcontainer start -->
        <content class="dmcontainer col-10 ">
            
            <!-- title start -->
            <div class="title">
                <h1>  <i class="fas fa-th-large px-3"></i>DM瀏覽</h1>
            </div>
            <!-- title end -->
            
            <!-- dmImage start -->
            <div id="image" class="dmImage pt-3 d-flex justify-content-center">
                    <img id="myimage" src="<c:url value='/images/DM/DM1.jpg'/>"  alt="dm">         
            </div>
            <!-- dmImage end -->

            <!-- filebuttoncontainer start -->
            <div class="filebuttoncontainer d-flex p-2 ">
                <div class="col-5 d-flex justify-content-end">
                    <button type="submit" class="btn-warning downloadBtn">DM檔案下載</button>
                </div>

                <div class="backnext col-7 d-flex align-items-center p-2">
                    <i class="fas fa-chevron-circle-left"></i>
                    <div>目前頁次/總頁次</div>
                    <i class="fas fa-chevron-circle-right"></i>
                </div>
            </div>
            <!-- filebuttoncontainer end -->

        </content>
        <!-- dmcontainer end -->

    </div>
    <!-- midlecontent end -->
    
    <!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
 <script type="text/javascript" src="/js/_02onlinedm.js"></script> 
</body>
</html>