<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<!-- CSS -->
     <link rel="stylesheet" href="<c:url value='/css/_02contactus.css' />"
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
    <!-- sweetAlert -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>  
    <!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

    <title>聯絡我們</title>
      
</head>
<body id="body">

    <!-- main start -->
    <div class="main">

      <!-- side-menu start -->
        <nav class="side-menu col-2 ">
          <div class="sideSpace"></div>
          <div class="button   d-flex justify-content-center align-items-center ">
              <a href="" class="sideBtn">客服Q&A</a>
          </div>
          <div class="button d-flex justify-content-center align-items-center ">
             <a href="" class="sideBtn">聯絡我們</a>
          </div>
        </nav>
        <!-- side-menu end -->
        

        <!-- Main Form start-->
        <div class=" col-10 p-3">
            <!-- form title start-->
            <div class="title col-12 ">
                <h1><i class="fas fa-th-large pe-3"></i>聯絡我們</h1>
            </div>
            <!-- title end -->
            

            <!-- form start -->
            <div class="container p-3 ">
                <form class="row g-3 form">
<!-- 功能:登入會員會自動顯示會員帳號/email/電話，未登入的話可以自己key -->
                    <!-- account&orderno start -->
                    <div class="col-6">
                        <label for="inputAccount" class="form-label">會員帳號:</label>
                        <input type="text" class="form-control" id="inputAccount">
                    </div>
                    <div class="col-6">
                        <label for="inputOrderno" class="form-label">訂單編號:</label>
                        <input type="text" class="form-control" id="inputOrderno">
                    </div>
                    <!-- account&orderno end -->
                    

                    <!-- email&phone start -->
                    <div class="col-6">
                        <label for="inputEmail" class="form-label">Email</label>
                        <input type="email" class="form-control" id="inputEmail" >
                    </div>
                    <div class="col-6">
                        <label for="inputPhone" class="form-label">連絡電話</label>
                        <input type="tel" class="form-control" id="inputPhone" >
                    </div>
                    <!-- email&phone end -->


                    <hr>


                    <!-- problemtype start -->
                    <div class="col-12 w-50 ">
                        <label for="inputProblemtype" class="form-label">問題種類:</label>
                        <select id="inputProblemtype" class="form-select">
                            <option selected>Choose...</option>
                            <option value="rent">rent</option>
                            <option value="buy">buy</option>
                            <option value="payment">payment</option>
                            <option value="delivery">delivery</option>
                            <option value="other">other</option>
                        </select>
                    </div>
                    <!-- problemtype end -->
                    

                    <!-- description start -->
                    <div class="col-12 ">
                        
                        <label for="inputDescription" class="form-label">問題敘述:</label>
                        <br>
                        <textarea name="description" id="inputDescription" cols="70" rows="10"></textarea>
                    </div>
                    <!-- description end -->



                    <!-- submit&upload start -->
                <div class="d-flex">
                  <button type="button" class="btn btn-warning me-2" onclick="track(this)">
                    送出表單
                </button>
                  <button type="submit" class="btn btn-secondary me-3">上傳檔案</button>
                  <input type="file" id="theFile" multiple>
                </div>
                  <!-- submit&upload end-->
               
                </form>   
            </div>
            <!-- form end -->           
        </div> 
         <!-- Main Form start-->
    </div>
     <!-- main end-->      
	 <!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
    <!-- bootstrap -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>   
  <script>
    function track() {
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: '表單送出成功',
      showConfirmButton: false,
      timer: 1000
    })
  } 
  </script>
</body>
</html>