<!-- <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>CodePen - Login Form - Diprella</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="_09login.css">

</head> -->

<!-- <body> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es" dir="ltr">

<head>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta charset="utf-8">
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700;800&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="./css/_05login.css">
<link rel="stylesheet" href="<c:url value='./css/commonStyle.css' />"
	type="text/css">
<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<!-- 一般bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- 生日引用  -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"
	rel="stylesheet">
	<!-- sweetAlert -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>  
<script type="text/javascript"></script>

<style type="text/css">
   span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}
</style>

<title>會員登入/註冊</title>


</head>

<body id="loginBody">
	<div id="body">
		<div id="content">
		<!-- 引入共同的頁首 -->
		<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />


		<!-- main start! -->
		<div class="loginMain">
			<div class="container a-container" id="a-container">
				<!-- login form start -->
				<form class="form" id="a-form" method="POST" action="login">
					<h2 class="form_title title  px-3">登入會員</h2>
					<input class="form__input" type="text" name="account" id="account"
						placeholder="帳號" value="${requestScope.user}">
					<input class="form__input" type="password" name="password" id="password" 
						placeholder="密碼" maxlength="12" value="${requestScope.password}">
					<div class="form__group">

						<label for="checkbox"> <input type="checkbox" name="rememberMe" 
							<c:if test="${requestScope.rememberMe==true}">
					checked='checked'
					</c:if>
							value="${requestScope.rm}">記住帳密
						</label>
					</div>
					
					<Font color='red' size="-3">${ErrorMsgKey.accountError}</Font>
					<Font color='red' size="-3">${ErrorMsgKey.Error}</Font>
					
					<button type="button" class="form__button button" id="button"
						data-toggle="modal" data-target="#exampleModalCenter">登入
					</button>
				</form>
				<!-- login form end -->
				<!-- <div> <a class="form__link">忘記密碼了嗎?您...失智了嗎?</a></div> -->
			</div>
			<div class="container b-container" id="b-container">
				<!-- register form start -->
				<form:form class="form" id="b-form" method="POST" modelAttribute="customer" enctype='multipart/form-data'>
					<h2 class="form_title title">註冊會員</h2>
					<c:choose>
						<c:when test='${customer.custId == null}'>
							<form:input class="form__input" id="account" type="text" 
								placeholder="帳號 " path='account'/>
							<form:errors path="account" cssClass="error" />
							<Font color='red' size="-3">${columnErrorMsg.accountError}</Font>							
						</c:when>
						<c:otherwise>
							<c:if test='${customer.custId != null}'>
								<form:hidden path='account' /> ${customer.account}<br>
							</c:if>
						</c:otherwise>
					</c:choose>
					<form:input class="form__input" id="password" type="password" maxlength="12"   placeholder="密碼" path='password'/>
					<form:errors path="password" cssClass="error" />
					<form:input class="form__input" id="custName" type="text" placeholder="姓名" path='custName'/> 
					<form:errors path="custName" cssClass="error" />
					<form:input class="form__input" id="birthday" type="text" placeholder="生日" path='birthday'/>
					<form:errors path="birthday" cssClass="error" />
					<form:input class="form__input" id="email" type="text" placeholder="信箱" path='email'/> 
					<form:errors path="email" cssClass="error" />
					<form:input class="form__input" id="phone" type="text" placeholder="手機" maxlength="10" path='phone'/>
					<form:errors path="phone" cssClass="error" />
					<form:input  class="form__input" path="Image" type='file'/>
	   	  	 		<form:errors path="Image"  cssClass="error" />
					<button type="submit" class="form__button button " onclick="track(this)"  id="button1">註冊</button>
					
				</form:form>
				<!-- register form end -->
				<!-- main end! -->
				<!-- 動態框  -->
			</div>
			<div class="switch" id="switch-cnt">
				<div class="switch__circle"></div>
				<div class="switch__circle switch__circle--t"></div>
				<div class="switch__container" id="switch-c1">
					<h2 class="switch__title title">Welcome Back !</h2>
					<p class="switch__description description">享用更多會員功能，請您登入</p>
					<button class="switch__button button switch-btn">立即註冊</button>
				</div>
				<div class="switch__container is-hidden" id="switch-c2">
					<h2 class="switch__title title">您好 !</h2>
					<p class="switch__description description">歡迎註冊加入我們，享用更多會員功能</p>
					<button class="switch__button button switch-btn">立即登入</button>
					<!-- 動態框  -->
				</div>
			</div>
		</div>



		<!--拼圖認證+彈跳 Start!-->
		<div class="modal fade" id="exampleModalCenter" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header2">
						<h4 class="modal-title" id="exampleModalCenterTitle">請完成拼圖驗證</h4>
					</div>
					<div class="modal-header">
						<h6>請拉動下方條把拼圖移至缺口中</h6>
					</div>
					<div class="modal-body">
						<div id="captcha">
							<div id="handle">
								<span></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--拼圖認證+彈跳 End!-->
		
		</div>
		   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" ></script>   
		
		
		
	<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
		
	</div>



	<!-- 註冊登入js參考網站:https://webdesignerwall.com/wdw-snippet/login-form-4 -->
	<script type="text/javascript" src="./js/_05loginOk.js"></script>
	<script type="text/javascript" src="./js/_05login.js"></script>
	<!-- 彈跳視窗js參考網站:https://www.youtube.com/watch?v=c7kIL-bV8ac -->
	<script type="text/javascript" src="./js/_05login_ven.js"></script>
</body>

</html>
