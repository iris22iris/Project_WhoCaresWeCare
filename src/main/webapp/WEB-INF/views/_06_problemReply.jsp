<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- CSS -->
     <link rel="stylesheet" href="<c:url value='/css/_06problemReplyQuery.css' />"
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
	<title>客服紀錄查詢</title>
	<script src="<c:url value='/js/ContactUs.js' />"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<script>
	function usSearch() {
		$.ajax({
			url : '/Whocares/_06_problemReply/${sessionScope.LoginOK}',
			type : "POST",
			async : false,
			data : {
				replyId : document.getElementById("enter").value
			},
	success : function(response) {
				if (response) {
					$('#accountNo').val(response.account);
					$('#orderNo').val(response.ordId);
					$('#inputEmail').val(response.email);
					$('#inputPhone').val(response.phone);
					$('#recordNum').val(response.phone);
					$('#recordDate').val(response.replyDate);
					$('#problemType').val(response.problemType);
					$('#record').val(response.replyContent);
					$('#inputDescription').val(response.replyContent);
					$('#inputreplyId').val(response.replyId);
					$('#inputContent').val(response.content);
					$('#inputformDate').val(response.formDate);
				}
			}
		});
	}
</script>





<body>
  <!-- main start -->
  <div id="body">
    <div id="content">
   <!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

      <!-- title start-->
      <div class="title">
        <div class="recordTitle ">
          <li>
            <i class="fas fa-th-large p-3" aria-hidden="true"></i>
            客服紀錄查詢
          </li>
        </div>
       <!-- 搜尋start -->
        <div class="container search pb-3">
          <form class="row align-items-center">
            <div class="col-3  d-flex justify-content-end">
            <label class = "enter">${customerBean.custName}您好，請輸入客服編號查詢：</label></div>
            <div class="col-3">
            <input type="search" class="form-control" id="enter"></div>
            <div class="col-1">
            <button  type="button" class="btn btn-warning" onclick="usSearch()">搜尋</button>
            </div>
          </form>
        </div>
        <!-- 搜尋end -->
      </div>      
      <!-- title end -->

    <!-- recordForm start 尚未查詢以前這個區塊隱藏problem-->
    <div class="container recordForm">
        
         <!-- account&orderno start -->
       <div class="row formRow">
          <div class="col-5">
            <label for="account" class="form-label">會員帳號:</label>
            <input type="text" class="form-control"  id="accountNo" disabled="disabled" > 
          </div>
          <div class="col-1"></div>
          <div class="col-5">
            <label for="orderNo" class="form-label">訂單編號:</label>
            <input type="text" class="form-control" id="orderNo" disabled="disabled">
          </div>
          <div class="col-1"></div>
        </div>
        <!-- account&orderno end -->
        
        <!-- email&phone start -->
        <div class="row formRow">
          <div class="col-5">
              <label for="Email" class="form-label">Email:</label>
              <input type="email" class="form-control" id="inputEmail" disabled="disabled">
          </div>
          <div class="col-1"></div>
          <div class="col-5">
              <label for="phone" class="form-label">連絡電話:</label>
              <input type="tel" class="form-control" id="inputPhone" disabled="disabled">
          </div>
          <div class="col-1"></div>
        </div>
        <!-- email&phone end -->

        <!-- service&date start -->
        <div class="row formRow">
          <div class="col-5">
            <label for="recordNum" class="form-label">客服編號:</label>
            <input type="number" class="form-control" id="inputreplyId" disabled="disabled">
          </div>
          <div class="col-1"></div>
          <div class="col-5">
            <label for="recordDate" class="form-label">表單時間:</label>
            <input type="datetime-local" class="form-control" id="inputformDate" disabled="disabled">
          </div>
          <div class="col-1"></div>
        </div>
        <!-- service&date end -->

        <hr>

        <!-- problemtype&replytime start -->
        <div class="row formRow">
          <div class="col-3">
              <label for="problemType" class="form-label">問題種類:</label>
              <input type="problemType" class="form-control" id="problemType" disabled="disabled">
          </div>
        </div>     
        <!-- problemtypee&replytime end -->
       
        <!-- description & reply start -->
        <div class="row formRow">
          <div class="col-6">
            <label for="record" class="form-label" >意見內容:</label>
            <br>
            <textarea name="description" id="inputContent" cols="70" rows="10" disabled="disabled" ></textarea>
          </div>
          <div class="col-6">
            <label for="inputDescription" class="form-label">客服回覆:</label>
            <br>
            <textarea name="description" id="inputDescription" cols="73" rows="10" disabled="disabled" ></textarea>
          </div>
        </div>
        <!-- description & reply end -->

        <!-- upload file start -->
        <div class="row formRow">
          <div class="col-12">              
            <input type="file" id="input" multiple="true" onchange="handleFiles(this.files)" >
            <div  style="text-align:right;">
              <span style="text-decoration:underline" style="vertical-align: text-top;">回覆日期:2021</span>
            </div>
          </div>  
        </div>
        <!-- upload file end -->
        
    </div>
    <!-- recordForm end -->
      
    <!-- btn start 返回-->
    <div class=" button col-12 pb-3 d-flex justify-content-center align-items-center">
      <button type="submit" class="btn btn-secondary">返回</button>
    </div>
    <!-- btn end 返回--> 

  </div>

  <!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
         
  
  </div>
  <!-- main end -->

  <!-- bootstrap -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>   
</body>
</html>