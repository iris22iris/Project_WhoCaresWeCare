<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/index.css" type="text/css">
    
<!--     <link rel="stylesheet" href="./css/index.css"> -->
    
     <title>Who Cares? We Care!</title>   
     <!-- bootstrap -->
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"></linkrel>
     <!-- icon -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body id="body">

  <!-- Main-header Start! -->
   <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <a class="navbar-brand w-25" href="index.html">
          <img src="<c:url value='./images/whiteLogo.png' />" alt="回到首頁" id="logo">
          
        </a>

      <!-- rwd toggler start -->
      <button class="navbar-toggler" type="button" 
      data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" 
      aria-controls="navbarSupportedContent" aria-expanded="false" 
      aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <!-- toggler end -->
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
<!-- 有空的話可以寫個功能是到這一頁前面就會有拐杖ICON,其他的則不會有 -->
        <!-- left Menu -->
        <ul class="navbar-nav  me-auto mb-lg-0 left-menu">
          <li class="nav-item ">
            <a class="nav-link textSize " href="_01aboutus.html"
            style="color:white;">
            關於我們</a>
          </li>
          <li class="nav-item">
            <a class="nav-link textSize " href="_03rentProductMenu.html"
            style="color:white;">
            租賃設備</a>
          </li>
          <li class="nav-item">
            <a class="nav-link textSize " href="#"
            style="color:white;">
            購物商城</a>
          </li>
          <li class="nav-item">
            <a class="nav-link textSize " href="_02q_a.html"
            style="color:white;">
            客服中心</a>
          </li>
<!-- 可以增加功能為登入才會顯示會員中心這個連結 -->
          <li class="nav-item"></li>
          <a class="nav-link textSize px-3" href="_05member_management.html"
          style="color:white;">
          會員中心</a>
          </li>
        </ul>
        <!-- right menu -->
        <li class="nav-item">
            <a class="nav-link" href="#" 
            style="padding: 0px;">
<!-- 選擇租賃設備清單/購物車清單 -->
            <img src="<c:url value='./images/cartIcon.png' />" alt="購物車"></a>
           
          </li>
        </ul>
<!-- 可以寫登入後換成會員圖片的功能 -->
        <li class="nav-item">
            <a class="nav-link" href="_05login.html" 
            style="padding: 0px;">
            <img src="<c:url value='./images/memberIcon.png' />" alt="會員"></a></a>
             
          </li>
        </ul>

        <!-- Search -->
        <form class="d-flex">
          <input class="form-control me-2" type="search" 
          placeholder="Search Product" aria-label="Search">
          <button class="btn btn-outline-warning" type="submit">
            <i class="fas fa-search"></i>
          </button>
        </form>
      </div>
    </div>
  </nav>
  <!-- Main-header End -->
   

<!-- 廣告圖片大小&button顏色 還須調整 -->
  <!-- Slider-img Start! -->
   <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active" data-bs-interval="5000">
        <img src="<c:url value='./images/indexAd1.jpg' />" class="d-block w-100" alt="ad1">
        
      </div>
      <div class="carousel-item" data-bs-interval="5000">
        <img src="<c:url value='./images/indexAd2.jpg' />" class="d-block w-100" alt="ad2">
        
      </div>
      <div class="carousel-item">
        <img src="<c:url value='./images/indexAd3.jpg' />" class="d-block w-100" alt="ad3">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  <!-- Slider-img End -->
   

  



  <div class="newsAndProduct">
  <!-- News Start! -->
    <div class="news px-4">
        <div class="newsAndProduct-Head">
            <h1><i class="fas fa-th-large px-2"></i>最新消息</h1>
          </div>
<!-- 功能：從最新消息裡抓第一篇的Head & Content並限制顯示字數 -->
        <div id="newsHead">
            2021年公益團體聯歡晚會
        </div>
        <div id="newsContent">
            護Cares?WeCare!為2021年公益團體聯歡晚會贊助廠商，
            活動當天提供100盒與中衛獨家聯名的口罩，給前一百名捐款的善心人士。活動資訊…
        </div>
        <div id="newsBotton" class="d-md-flex justify-content-md-end">
<!-- 按鈕顏色需調整 -->
            <button type="button" class="btn btn-outline-light mt-3" 
            href="#">
            看更多...
          </button>
        </div>
    </div>
  <!-- News End -->

  <!-- product-card Start! -->
    <div class="product-card pe-4">
      <div class="newsAndProduct-Head">
        <h1><i class="fas fa-th-large px-2"></i>精選商品</h1>
      </div>
     <div class="container pb-4">
        <div class="row px-6">
          <div class="col-12 col-sm-6 col-md-3 mt-2">
            <div class="card" style="width: 105%;">
              <a href="#商品網址">
              <img src="<c:url value='./images/product/A0002.jpg' />" class="card-img-top" alt="...">
              </a>
              <div class="card-body">        
                  <h5 class="card-title" >輔助功能型摺疊輪椅</h5>
                  <div class="row-2">
                    <p class="card-text">$8888</p>
                  </div>
                  <div class="row-3 pt-2">
                    <select class="form-select" style="width: 45%;" aria-label="Default select example">
                      <option selected>數量</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                    <a href="#" class="btn btn-warning" >加入購物車</a>
                  </div>           
              </div>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3 mt-2">
            <div class="card" style="width: 105%;">
              <a href="#商品網址">
              <img src="<c:url value='./images/product/A0001.jpg' />" class="card-img-top" alt="...">
              </a>
              <div class="card-body">        
                  <h5 class="card-title" >輔助功能型摺疊輪椅</h5>
                  <div class="row-2">
                    <p class="card-text">$8888</p>
                  </div>
                  <div class="row-3 pt-2">
                    <select class="form-select"  style="width: 45%;" aria-label="Default select example">
                      <option selected>數量</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                    <a href="#" class="btn btn-warning">加入購物車</a>
                  </div>           
              </div>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3 mt-2">
            <div class="card" style="width: 105%;">
              <a href="#商品網址">
              <img src="<c:url value='./images/product/A0003.jpg' />" class="card-img-top" alt="...">
              </a>
              <div class="card-body">        
                  <h5 class="card-title" >輔助功能型摺疊輪椅</h5>
                  <div class="row-2">
                    <p class="card-text">$8888</p>
                  </div>
                  <div class="row-3 pt-2">
                    <select class="form-select"  style="width: 45%;" aria-label="Default select example">
                      <option selected>數量</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                    <a href="#" class="btn btn-warning">加入購物車</a>
                  </div>           
              </div>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3 mt-2">
            <div class="card" style="width: 105%;">
              <a href="#商品網址">
              <img src="<c:url value='./images/product/A0004.jpg' />" class="card-img-top" alt="...">
              </a>
              <div class="card-body">        
                  <h5 class="card-title" >輔助功能型摺疊輪椅</h5>
                  <div class="row-2">
                    <p class="card-text">$8888</p>
                  </div>
                  <div class="row-3 pt-2">
                    <select class="form-select"  style="width: 45%;" aria-label="Default select example">
                      <option selected>數量</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                    <a href="#" class="btn btn-warning">加入購物車</a>
                  </div>           
              </div>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3 mt-2">
            <div class="card" style="width: 105%;">
              <a href="#商品網址">
              <img src="<c:url value='./images/product/A0005.jpg' />" class="card-img-top" alt="...">
              </a>
              <div class="card-body">        
                  <h5 class="card-title" >輔助功能型摺疊輪椅</h5>
                  <div class="row-2">
                    <p class="card-text">$8888</p>
                  </div>
                  <div class="row-3 pt-2">
                    <select class="form-select"  style="width: 45%;" aria-label="Default select example">
                      <option selected>數量</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                    <a href="#" class="btn btn-warning">加入購物車</a>
                  </div>           
              </div>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3 mt-2">
            <div class="card" style="width: 105%;">
              <a href="#商品網址">
              <img src="<c:url value='./images/product/B0001.png' />" class="card-img-top" alt="...">
              </a>
              <div class="card-body">        
                  <h5 class="card-title" >輔助功能型摺疊輪椅</h5>
                  <div class="row-2">
                    <p class="card-text">$8888</p>
                  </div>
                  <div class="row-3 pt-2">
                    <select class="form-select"  style="width: 45%;" aria-label="Default select example">
                      <option selected>數量</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                    <a href="#" class="btn btn-warning">加入購物車</a>
                  </div>           
              </div>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3 mt-2">
            <div class="card" style="width: 105%;">
              <a href="#商品網址">
              <img src="<c:url value='./images/product/A0003.jpg' />" class="card-img-top" alt="...">
              </a>
              <div class="card-body">        
                  <h5 class="card-title" >輔助功能型摺疊輪椅</h5>
                  <div class="row-2">
                    <p class="card-text">$8888</p>
                  </div>
                  <div class="row-3 pt-2">
                    <select class="form-select"  style="width: 45%;" aria-label="Default select example">
                      <option selected>數量</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                    <a href="#" class="btn btn-warning">加入購物車</a>
                  </div>           
              </div>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3 mt-2">
            <div class="card" style="width: 105%;">
              <a href="#商品網址">
              <img src="<c:url value='./images/product/A0004.jpg' />" class="card-img-top" alt="...">
              </a>
              <div class="card-body">        
                  <h5 class="card-title" >輔助功能型摺疊輪椅</h5>
                  <div class="row-2">
                    <p class="card-text">$8888</p>
                  </div>
                  <div class="row-3 pt-2">
                    <select class="form-select"  style="width: 45%;" aria-label="Default select example">
                      <option selected>數量</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                    <a href="#" class="btn btn-warning">加入購物車</a>
                  </div>           
              </div>
            </div>
          </div>
        </div>
     </div> 
    </div>
  <!-- Product-card End -->
  </div>






  <!-- footer Start! -->
    <div class="footer container-fluid">
      <!-- left-footer -->
      <div id="left-footer">
        <dl class="row pt-4 pb-2 ps-4">
          <dt class="col-sm-3 d-flex justify-content-end">
            <i class="fas fa-map-marker-alt pe-1"></i>
            公司地址:
          </dt>
          <dd class="col-sm-9">
            台北市大安區忠孝東路三段1號
          </dd>      
          <dt class="col-sm-3 d-flex justify-content-end">
            <i class="fas fa-phone pe-1"></i>
            連絡電話:
          </dt>
          <dd class="col-sm-9">
            (02) 2211-3344
          </dd>       
          <dt class="col-sm-3 d-flex justify-content-end">
            <i class="far fa-smile pe-1"></i>
            服務時段:
          </dt>
          <dd class="col-sm-9">
            週一至週五(例假日除外) 9:00AM - 17:30PM
          </dd>
      </div>
      <!-- right-footer -->
      <div id="right-footer">
        <ul class="list-inline pt-4 d-flex justify-content-start align-items-end">
          <li class="list-inline-item px-4">
            <i class="fab fa-facebook-square"></i>
          </li>
          <li class="list-inline-item px-4">
            <i class="fab fa-instagram"></i>
          </li>
          <li class="list-inline-item px-4">
            <i class="fab fa-line"></i>
          </li>
        </ul>
        <div class="copyright">
         &nbsp;&nbsp; All Content Copyright &copy; 2021
        </div>
      </div>
    </div>
  <!-- footer End! -->
    

  <!-- bootstrap -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>   
</body>
</html>
