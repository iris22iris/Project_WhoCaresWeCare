<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='/css/_05memberProfile.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />" type="text/css">
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
		searchBox();
	$("input[type=radio][name='gender'][value='${customer.gender}']").prop("checked", true);
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/querySelect/A,B' />", true);
	xhr.send();	
	
	xhr.onreadystatechange = function() {
	 // 伺服器請求完成
	 
	    if (xhr.readyState == 4 && xhr.status == 200) {
		   var querySelect = JSON.parse(xhr.responseText);
			$("#city").append("<option value='" + 0 + "'selected='selected' disabled>" + "請選擇" + "</option>");
		   querySelect.forEach(function (item) {
			   $("#city").append("<option value='" + item.city + "'>" + item.city + "</option>");
           });
		   $("#city").val('${customer.city}')
	    }
     }
}

function upData() {
	var divResult = document.getElementById('resultMsg');
	var custNameResult = document.getElementById('custNameResult');
	var passWordResult = document.getElementById('passWordResult');
	var inputIDResult = document.getElementById('inputIDResult');
	
	let formData = new FormData();
	formData.append('custId', ${sessionScope.LoginOK});
	formData.append('custName', $('#custName').val());
	formData.append('passWord', $('#passWord').val());
	formData.append('idNumber', $('#inputID').val());
	formData.append('phone', $('#phone').val());
	formData.append('email', $('#email').val());
	formData.append('birthday', $('#birthday').val());
	formData.append('gender', $('input[type=radio][name="gender"]:checked').val());
	formData.append('city', $('#city').find("option:selected").text());
	formData.append('nickName', $('#nickName').val());
	formData.append('address', $('#address').val());
	formData.append('image', $('#custImage')[0].files[0] == undefined ? null : $('#custImage')[0].files[0]);

// 	if($("#form1").validate().form() == true) {
		$.ajax({
			url : '${pageContext.request.contextPath}/_05_EditmemberProfile',
			type : "POST",
		    processData: false,
			contentType: false,
    		mimeType: 'multipart/form-data',
    		data :formData,	
    		datatype: 'json',	
			success: function(response){
				const responseData = JSON.parse(response);
				console.log(Object.keys(responseData));
				if(!responseData.success){
					alert('修改會員失敗!');
					$("#inputEmailResult").html(responseData.emailError);
					$("#passWordResult").html(responseData.passWordError);
					$("#inputIDResult").html(responseData.idNumberError);
					$("#inputPhoneResult").html(responseData.phoneError);
					$("#custNameResult").html(responseData.custNameError);
				} else {
					$('Font').html('&nbsp;');
					//跳訊息提示
			        alert('修改會員成功!');
				}
		    },
		});
// 	}
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

			<!-- title start  -->
			<div class="pageTitle">會員資料修改</div>
			<!-- title end -->

			<!-- form start -->
			<div class="container profileForm">
				<!-- memberImg start -->
				<div class="col-3 memberImgUpload">
					<div class="col-12 memberImg">
						<img src='${pageContext.request.contextPath}/getMemberImg?custId=${customer.custId}' 
							 id="showImage" alt="" width="200" height="200">
					</div>
					<div class="col-12 uploadBtn">
						<input id="custImage" type="file" onchange="handleFiles(event)"">
					</div>
<!-- 					<button type="submit" class="btn btn-secondary">清除</button> -->
				</div>
				<!-- memberImg end  -->

				<!-- inputProfile start -->
				<div class="col-9 inputProfile">
					<div
						class="col-9  p-3 d-flex justify-content-center align-items-center">
						<!-- 輸入資料區表格star -->
						<form class="row g-3" id="form1">
							<div id='resultMsg' style="height: 18px; display: none;"></div>

							<div class="col-3 ">
								<label for="custName" class="form-label">會員姓名:</label>
							</div>
							<div class="col-9 ">
								<input type="text" class="form-control" name="custName"
									id="custName" value="${customer.custName}">
							</div>
							<div class="container">
								<div class="row justify-content-end">
									<div class="col-9">
										<Font color="red" size="-3" id="custNameResult">&nbsp;</Font>
									</div>
								</div>
							</div>
							<div class="col-3">
								<label for="nickName" class="form-label">會員暱稱:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" id="nickName"
									id="nickName" value="${customer.nickName}">
							</div>

							<div class="col-3">
								<label for="passWord" class="form-label">密碼:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" name="passWord" maxlength="12"
									id="passWord" value="${customer.password}">
							</div>
							<div class="container">
								<div class="row justify-content-end">
									<div class="col-9">
										<Font color="red" size="-3" id="passWordResult">&nbsp;</Font>
									</div>
								</div>
							</div>
							<div class="col-3">
								<label for="inputID" class="form-label">身分證字號:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" id="inputID"
									value="${customer.idNumber}">
							</div>
    						<div class="container">
								<div class="row justify-content-end">
									<div class="col-9">
										<Font color="red" size="-3" id="inputIDResult">&nbsp;</Font>
									</div>
								</div>
							</div>
							<div class="col-3">
								<label>性別:</label>
							</div>
							<div class=" gender col-9 ">
								<input type="radio" name="gender" id="male"
									value="male"> 男
								<input type="radio" name="gender" id="female" 
									value="female"> 女
								<input type="radio" name="gender" id="multiple" 
									value="multiple"> 多元
								<input type="radio" name="gender" id="no"
									value="no"> 無
							</div>



							<div class="col-3">
								<label for="phone" class="form-label">聯絡電話:</label>
							</div>
							<div class="col-9">
								<input type="text" class="form-control" id="phone"
									value="${customer.phone}">
							</div>
							<div class="container">
								<div class="row justify-content-end">
									<div class="col-9">
										<Font color="red" size="-3" id="inputPhoneResult">&nbsp;</Font>
									</div>
								</div>
							</div>

							<div class="col-3">
								<label for="email" class="form-label" >EMAIL:</label>
							</div>
							<div class="col-9">
								<input type="email" class="form-control" size="50" id="email"
									value="${customer.email}">
							</div>
							<div class="container">
								<div class="row justify-content-end">
									<div class="col-9">
										<Font color="red" size="-3" id="inputEmailResult">&nbsp;</Font>
									</div>
								</div>
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
							<div class="col-9">
								<input data-for="birthday" class="form-control"
									type="text" id="birthday" value="${customer.birthday}">
							</div>

							<!-- submit btn start   -->
							<div class="col-3"></div>
							<div class="col-9 submitBtn">
								<div class="col-2  ">
									<button type="button" class="btn btn-warning"
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
												<button type="button" class="btn btn-warning" data-bs-dismiss="modal"
													onclick="upData()">確認修改</button>
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
								<div class="col-2">
<%-- 									<a href="${pageContext.request.contextPath}/_05_member_management"><button --%>
<!-- 											class="btn btn-secondary" data-bs-toggle="modal" -->
<!-- 											data-bs-target="#exampleModal">取消</button></a> -->
								</div>
							</div>
					<!--submit btn end   -->

						</form>
					</div>
					<!-- 輸入資料區表格end -->



				</div>
				<!-- inputProfile end -->
			</div>
			<!-- form end -->
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