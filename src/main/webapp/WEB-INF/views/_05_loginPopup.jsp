<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='./css/_05loginPopup.css' />"
	type="text/css">
	<!-- bootstrap -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" data-ntegrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" data-crossorigin="anonymous"></link>
      <!-- icon -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" data-integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" data-crossorigin="anonymous" data-referrerpolicy="no-referrer" /> 

<!-- sweetAlert -->
     <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>  
     
     <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" data-integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" data-crossorigin="anonymous"></script>
     <script defer src="https://use.fontawesome.com/4e44561671.js"></script>
<!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
	<title>會員登入popup</title>
</head>
<body id="body">
<div class="col-12 d-flex justify-content-evenly align-items-center">
            <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal">
                會員登入
            </button>
</div>
<!-- modal fade  star  登入小視窗 開始 bg-warning text-dark-->                                        
    

                              
                   
 <div id="contentpopup">
            

        </div>
  							






<!-- bootstrap -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" data-integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" data-crossorigin="anonymous"></script>
<script type="text/javascript" src="./js/_05loginPopup3.js"></script>


</body>
</html>