<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='/css/_05memberProfile.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
window.onload = function() { 
	$("input[type=radio][name='gender'][value='${customer.gender}']").prop("checked", true);
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/querySelect/A,B' />", true);
	xhr.send();	
	var message = "";
	
	xhr.onreadystatechange = function() {
	 // 伺服器請求完成
	 
	    if (xhr.readyState == 4 && xhr.status == 200) {
		   var querySelect = JSON.parse(xhr.responseText);
		   
			$("#city").append("<option value='" + 0 + "'selected='selected' disabled>" + "請選擇" + "</option>");
// 			$("#city").append("<option value='0'>請選擇</option>"); 
		   querySelect.forEach(function (item) {
			   $("#city").append("<option value='" + item.id + "'>" + item.city + "</option>");
           });
		   
	    }
     }
}

function upData() {
	
	var divResult = document.getElementById('resultMsg');
	var custNameResult = document.getElementById('custNameResult');
	var passWordResult = document.getElementById('passWordResult');
	var inputIDResult = document.getElementById('inputIDResult');
	var sub = document.getElementById('sub');
// 	var custImage = document.getElementById('custImage');

	
// 	var showImage = $('#showImage').val();
	var custName = $('#custName').val();
	var nickName = $('#nickName').val();
	var password = $('#passWord').val();
	var inputID = $('#inputID').val();
	var phone = $('#phone').val();
	var email = $('#email').val();
	var address = $('#address').val();
	var birthday = $('#birthday').val();
	var gender = $('#male').val();
	var city = $('#city').val();
	
	var xhr1 = new XMLHttpRequest();
		xhr1.open("PUT", "<c:url value='/_05_EditmemberProfile/' />" + ${id}, true);
		var jsonCustomer = {
				custId:${id},
				custName:$('#custName').val(),	
				password:$('#passWord').val(),	
				idNumber:$('#inputID').val(),
				phone:$('#phone').val(),	
				email:$('#email').val(),
				birthday:$('#birthday').val(),
				gender:$('input[type=radio][name="gender"]:checked').val(),
				city:$('#city').val(),
				nickName:$('#nickName').val(),
				address:$('#address').val(),
// 				image:$('#showImage').attr("src")
		   		}
	   		xhr1.setRequestHeader("Content-Type", "application/json");
	   		xhr1.send(JSON.stringify(jsonCustomer));


	   		xhr1.onreadystatechange = function() {
				// 伺服器請求完成
   		if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 201) ) {
      		result = JSON.parse(xhr1.responseText);
      		if (result.fail) {
		 		divResult.innerHTML = "<font color='red' >"
					+ result.fail + "</font>";
	  		} 

	  		if (result.custNameError) {
	  			custNameResult.innerHTML = "<font color='RED'>"
					+ result.custNameError + "</font>";
					$('#custNameResult').show();
	 		} 

	  		if (result.passWordError) {
	  			passWordResult.innerHTML = "<font color='RED'>"
					+ result.passWordError + "</font>";
					$('#passWordResult').show();
	 		} 

	  		if (result.idNumberError) {
	  			inputIDResult.innerHTML = "<font color='RED'>"
					+ result.idNumberError + "</font>";
					$('#inputIDResult').show();
	 		} 
		} 
    }
}

function handleFiles(e){
    console.info(e.target.files[0]);//圖片文件
    var dom =$("input[id^='custImage']")[0];
    console.info(dom.value);//這個是文件的路徑 C:\fakepath\icon (5).png
    console.log(e.target.value);//這個也是文件的路徑和上面的dom.value是一樣的
    var reader = new FileReader();
    reader.onload = (function (file) {
        return function (e) {
           console.info(this.result); //這個就是base64的數據了
            var sss=$("#showImage");
            $("#showImage")[0].src=this.result;
        };
    })(e.target.files[0]);
    reader.readAsDataURL(e.target.files[0]);
}


</script>

<title>會員資料修改</title>
</head>
<body>
	<div id="body">
		<div id="content">
			<!-- 引入共同的頁首 -->
			<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />


			<!-- changmtitle star  會員修改資料標題star -->
			<div class="changmtitle col-3 text-center p-3  ">
				<ol>
					<li><i class="fas fa-th-large" aria-hidden="true"></i>會員資料修改</li>
				</ol>
			</div>

			<!-- changmtitle 會員修改資料標題end -->

			<!-- main container star  修改會員資料的表單star -->

			<div
				class="main container p-3 col-12 justify-content-center text-center ">
				<!-- main containerfile star  修改會員資料的表單圖片上傳區star -->
				<div class=" col-3 p-3  ">
					<!--  圖片star  圖片star -->
					<div class="button  col-12 p-3 ">
						<div class="col-6" style="margin-left: 40px">
							<input id="custImage" type="file" onchange="handleFiles(event)">
						</div>
						<div class="col-12">
							<img src="<c:url value='/getMemberImg?custId=${customer.custId}'/>" id="showImage" alt="" width="150" height="150">
							<div class="col-12 p-3">
								<button type="submit" class="btn">清除</button>
							</div>
						</div>
					</div>
					<!--  按鈕end  按鈕end -->
				</div>
				<!-- main containerfile end  修改會員資料的表單圖片上傳區end -->

				<!-- 輸入資料區star  輸入資料區star -->
				<div class=" col-9 p-3">
					<div
						class="col-9  p-3 d-flex justify-content-center align-items-center">
						<!-- 輸入資料區表格star -->
						<form class="row g-3">
							<div id='resultMsg' style="height: 18px; display: none;"></div>

							<div class="col-3 ">
								<label for="custName" class="form-label">會員姓名:</label>
							</div>
							<div class="col-9 ">
								<input type="text" class="form-control" name="custName"
									id="custName" value="${customer.custName}">
							</div>
							<div id='custNameResult' style="height: 18px; display: none;"></div>


							<div class="col-3">
								<label for="nickName" class="form-label">會員暱稱:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" id="nickName"
									id="nickName" value="${customer.nickName}">
							</div>
							<div id='nickNameResult' style="height: 18px; display: none;"></div>


							<div class="col-3">
								<label for="passWord" class="form-label">密碼:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" name="passWord" maxlength="12"
									id="passWord" value="${customer.password}">
							</div>
							<div id='passWordResult' style="height: 18px; display: none;"></div>


							<div class="col-3">
								<label for="inputID" class="form-label">身分證字號:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" id="inputID"
									value="${customer.idNumber}">
							</div>
							<div id='inputIDResult' style="height: 18px; display: none;"></div>


							<div class="col-3">
								<label>性別:</label>
							</div>
							<div class=" choco col-9 ">
								<div class="col-4">
									<label><input type="radio" name="gender" id="male"
										value="male">男</label> <label><input type="radio"
										name="gender" id="female" value="female">女</label> <label><input
										type="radio" name="gender" id="multiple" value="multiple">多元</label>
									<label><input type="radio" name="gender" id="no"
										value="no">無</label><br>
								</div>
							</div>



							<div class="col-3">
								<label for="phone" class="form-label">聯絡電話:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" id="phone"
									value="${customer.phone}">
							</div>

							<div class="col-3">
								<label for="email" class="form-label">EMAIL:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" size="50" id="email"
									value="${customer.email}">
							</div>


							<div class="col-3">
								<label for="city" class="form-label">居住城市:</label>
							</div>
							<div class="col-9">
								<select id="city" class="form-select" name="city">
								
								</select>
							</div>

							
							<div class="col-3">
								<label for="address" class="form-label">居住地址:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" size="50" id="address"
								 value="${customer.address}">
							</div>

							<div class="col-3">
								<label for="birthday" class="form-label">出生(西元):</label>
							</div>
							<div class="col-3">
								<label><input data-for="birthday" class="form-label"
									type="text" id="birthday" value="${customer.birthday}"></label>
							</div>

						</form>
						<!-- 輸入資料區表格end -->
					</div>
					<!-- 輸入資料區end  輸入資料區end -->
					<!-- 輸入資料區按鈕star   -->
					<!-- Button trigger modal -->
					<!-- Modal -->

					<div
						class="col-7 p-1  d-flex justify-content-center align-items-center">

						<div class="col-4  ">
							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								送出</button>
						</div>
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">會員資料修改</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">確定要保存變更資料嗎?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" data-bs-dismiss="modal"
											onclick="upData()">確認修改</button>
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>

						<div class="col-3">
							<a href="<c:url value='/_05_member_management' />"><button
									class="btn btn-primary" data-bs-toggle="modal"
									data-bs-target="#exampleModal">取消</button></a>
						</div>

					</div>
					<!-- 輸入資料區按鈕end   -->

				</div>
				<!-- main container end  修改會員資料的表單end -->
			</div>
		</div>
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

	</div>
<script>

function track() {
alert("註冊成功");
Swal.fire({
      position: 'center',
      icon: 'success',
      title: '表單送出成功',
      showConfirmButton: false,
      timer: 1000
    
  } 
  
 )
 }
    
  </script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>