<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"> 
<link rel="stylesheet" href="<c:url value='./css/_05memberProfile.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='./css/commonStyle.css' />"
	type="text/css">
<!-- bootstrap -->
<link
	 href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	 rel="stylesheet" data-integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" data-crossorigin="anonymous">

<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

<title>會員資料修改</title>
</head>
<body>
	<div id="body">
<!-- changmtitle star  會員修改資料標題star -->
 <div class="changmtitle col-3 text-center p-3  ">
           <ol> <li>
             <i class="fas fa-th-large" aria-hidden="true"></i>會員資料修改
             </li>
            </ol>
        </div>

<!-- changmtitle 會員修改資料標題end -->

<!-- main container star  修改會員資料的表單star -->
     
    <div class="main container p-3 col-12 justify-content-center text-center ">
      <!-- main containerfile star  修改會員資料的表單圖片上傳區star -->
        <div class=" col-3 p-3  ">
            <!--  圖片star  圖片star -->
                <div class="col-12  d-flex justify-content-center align-items-center ">
                   <a href="#"> <img
								src="<c:url value='./images/product/A0001.jpg' />"
								class="cuspic" alt='cuspic'>
					</a>
                </div>
            <!--  圖片end  圖片end -->
            
         <!--  按鈕star  按鈕star -->
                <div class="button  col-12 p-3 ">
          
                   <div class="col-6">
                        
                        <input type="file" id="input" data-multiple="true" onchange="handleFiles(this.files)">
                    </div>   
<div class="col-12">
                       <div class="col-12 p-3">  
                           <button type="submit"  class="btn" >上傳圖片</button>
                        <button type="submit" class="btn"  >清除</button>
                        </div>
                       
                    </div>
                </div>
                <!--  按鈕end  按鈕end -->
        </div>
<!-- main containerfile end  修改會員資料的表單圖片上傳區end -->
        
<!-- 輸入資料區star  輸入資料區star -->
 <div class=" col-9 p-3">
            <div class="col-9  p-3 d-flex justify-content-center align-items-center">
<!-- 輸入資料區表格star -->
                <form class="row g-3">
                    
                    <div class="col-3 ">
                        <label for="inputname" class="form-label" >會員姓名:</label> 
                    </div>
                    <div class="col-9 ">
                        <input type="text"  class="form-control" id="inputname">
                    </div>
                 
                    <div class="col-3">
                        <label for="inputothername" class="form-label">會員暱稱:</label>
                    </div>
                    <div class="col-9">
                        <input type="text" class="form-control" id="inputothername">
                    </div>
                    <div class="col-3">
                      <label for="inputothername" class="form-label">密碼:</label>
                  </div>
                  <div class="col-9">
                      <input type="text" class="form-control" id="inputothername">
                  </div>
                    
                    <div class="col-3">
                        <label for="inputID" class="form-label">身分證字號:</label>
                    </div>
                    <div class="col-9">
                        <input type="text" class="form-control" id="inputID">
                    </div>
                    <div class="col-3">
                    <label>性別:</label>
                    </div>
                    <div class=" choco col-9 ">
                        <div class="col-4">
                        <label><input type="radio" name="gender" value="male">男</label>
                        <label><input type="radio" name="gender" value="female">女</label>
                        <label><input type="radio" name="gender" value="multiple">多元</label>
                        <label><input type="radio" name="gender" value="no">無</label><br>
                    </div>
                    </div>
                    <div class="col-3">
                        <label for="inputphone" class="form-label">聯絡電話:</label>
                    </div>
                    <div class="col-9">
                        <input type="text" class="form-control" id="inputphone">
                    </div>

                    <div class="col-3">
                        <label for="inputemail" class="form-label">EMAIL:</label>
                    </div>
                    <div class="col-9" >
                        <input type="text" class="form-control" size="50" id="inputemail">
                    </div>
                    <div class="col-3">
                        <label for="inputcity" class="form-label">居住城市:</label>
                    </div>
                    <div class="col-9">

                        <select id="inputcity" class="form-select" name="city">
                          <option value="請選擇">請選擇</option>
                            <option value="台北">台北</option>
                            <option value="新北">新北</option>
                            <option value="高雄">高雄</option>
                            <option value="台中">台中</option>
                            <option value="桃園">桃園</option>
                            <option value="基隆">基隆</option>
                            <option value="台南">台南</option>
                            <option value="新竹">新竹</option>
                            <option value="新竹">新竹縣</option>
                            <option value="苗栗縣">苗栗縣</option>
                            <option value="彰化縣">彰化縣</option>
                            <option value="南投縣">南投縣</option>
                            <option value="雲林縣">雲林縣</option>
                            <option value="嘉義">嘉義</option>
                            <option value="嘉義縣">嘉義縣</option>
                            <option value="屏東縣">屏東縣</option>
                            <option value="宜蘭縣">宜蘭縣</option>
                            <option value="花蓮縣">花蓮縣</option>
                            <option value="台東縣">台東縣</option>
                        </select>
                    </div>

                    <div class="col-3">
                        <label for="inputaddress" class="form-label">居住地址:</label>
                    </div>
                    <div class="col-9">
                        <input type="text" class="form-control" size="50" id="inputaddress">
                    </div>
                    
                    <div class="col-3">
                        <label for="inputbirthday" class="form-label" data-type="date" data-name="birthday" >出生(西元):</label>

                      
                    </div>
                    <div class="col-3">
                      <label><input data-for="inputbirthday" class="form-label"  type="date" name="birthday" ></label> 
                    </div>
                </form>
<!-- 輸入資料區表格end -->          
        </div>   
<!-- 輸入資料區end  輸入資料區end --> 

<!-- 輸入資料區按鈕star   --> 



<!-- Button trigger modal -->


<!-- Modal -->

                <div class="col-7 p-1  d-flex justify-content-center align-items-center">

                    <div class="col-4  ">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                          送出</button>
                    </div>
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">會員資料修改</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            確定要保存變更資料嗎?
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-primary">確認修改</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                          </div>
                        </div>
                      </div>
                    </div>
                
                    <div class="col-3">
                        <button type="submit" class="btn ">取消</button>
                    
                    </div>

                </div>
<!-- 輸入資料區按鈕end   --> 

    </div>  
<!-- main container end  修改會員資料的表單end -->
    </div> 
    </div> 
<!-- footer End! -->
           <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" data-integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" data-crossorigin="anonymous"></script>


<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

</body>
</html>