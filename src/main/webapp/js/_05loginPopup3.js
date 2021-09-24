
document.getElementById("contentpopup").innerHTML =
	'' +
	'<div class="modal fade " id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
	'        <div class="modal-dialog col-12">' +
	'                <div id="login" class="modal-content">' +
	'                    <h2 class="modal-title1 p-3 col-12 d-flex justify-content-evenly " id="exampleModalLabel">登入會員</h2>' +
	'                        <div class="containerPOPUP p-3  ">' +
	'                            <form class="row g-3 form">' +
	'                                <div class="col-3 justify-content-evenly p-1  d-flex align-items-center ">' +
	'                                    <label for="inputAccount" class="form-label">會員帳號 :</label>' +
	'                                </div>' +
	'                                <div class="col-9">' +
	'                                    <input type="text" class="form-control  " id="inputAccount">' +
	'                                </div>' +
	'                                <div class="col-3 justify-content-evenly p-1  d-flex align-items-center">' +
	'                                    <label for="inputAccount" class="form-label">會員密碼 :</label>' +
	'                                </div>' +
	'                                <div class="col-9">' +
	'                                    <input type="text" class="form-control" id="inputPassword">' +
	'                                </div>' +
	'                                <div class="form__group  ">' +
	'                                    <label>' +
	'                                        <input style="margin-left:120px;" type="checkbox" name="remember" id="inputRememberMe" > 記住帳密  ' +
	'                                    </label>' +
	'                                </div>' +
	'                                <div class="col-3">' +
	'                                    <label for="inputAccount" class="form-label justify-content-evenly p-1  d-flex align-items-center">圖形驗證碼:</label>' +
	'                                </div>' +
	'                                <div class="col-6">' +
	'                                    <input type="text" class="form-control" id="inputCanvas">' +
	'                                </div>' +
	'                                <div class="input-group  " id="box2">' +
	'                                    <span><canvas id="canvas" width="180" height="65"></canvas>' +

	'                                        <a href="#" id="changeImg">換一張</a></span>' +
	'                                </div>' +
	'                                <div class="modal-footer d-flex justify-content-center align-items-center ">                                   ' +
	'                                    <div class=" ">                       ' +
	'                                        <div class="button   d-flex justify-content-center align-items-center ">' +
	'							<button type="button" id="id_close" class="btn btn-secondary" data-bs-dismiss="modal" style="display:none"></button>' +
	'                                            <input type="button" value="Login" onclick="popupLogin()" style="margin-left:10px;"  class=" btn btn-primary d-flex justify-content-center align-items-center" data-bs-dismiss="modal"></input>' +
	'                                        </div>' +
	'                                        <nav class="hyperlink col-12 d-flex justify-content-center ">' +
	'                                            <div class="Hyperlinkbutton    ">' +
	'                                                <a href="" class="sideBtn">尚未註冊</a>' +
	'                                                <a href="" class="sideBtn">忘記密碼</a>' +
	'                                            </div>' +
	'                                        </nav>' +
	'                                    </div>' +
	'                                </div>' +
	'                            </form>' +
	'                        </div>' +
	'                </div>' +
	'        </div>' +
	'    </div>'
	;






function popupLogin() {
	$.ajax({
		type: 'POST',
		url: "/Whocares/login_api",

		data : {
				account : $("#inputAccount").val(),
				password : $("#inputPassword").val(),
				rememberMe : $("#inputRememberMe").val(),
			},
//		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',

//		success: function(data) {
//			console.log(data);
//			if (data != "ok") {
//				alert("登入錯誤!");
//			}
//			document.getElementById("id_close").click();
//		}
	});


	/**生成一個隨機數**/
	function randomNum(min, max) {
		return Math.floor(Math.random() * (max - min) + min);
	}
	/**生成一個隨機色**/
	function randomColor(min, max) {
		var r = randomNum(min, max);
		var g = randomNum(min, max);
		var b = randomNum(min, max);
		return "rgb(" + r + "," + g + "," + b + ")";
	}
	drawPic();
	document.getElementById("changeImg").onclick = function(e) {
		e.preventDefault();
		drawPic();
	}

	/**繪製驗證碼圖片**/
	function drawPic() {
		var canvas = document.getElementById("canvas");
		var width = canvas.width;
		var height = canvas.height;
		var ctx = canvas.getContext('2d');
		ctx.textBaseline = 'bottom';

		/**繪製背景色**/
		ctx.fillStyle = randomColor(180, 240); //顏色若太深可能導致看不清
		ctx.fillRect(0, 0, width, height);
		/**繪製文字**/
		var str = 'ABCEFGHJKLMNPQRSTWXY123456789';
		for (var i = 0; i < 4; i++) {
			var txt = str[randomNum(0, str.length)];
			ctx.fillStyle = randomColor(50, 160);  //隨機生成字體顏色
			ctx.font = randomNum(15, 40) + 'px SimHei'; //隨機生成字體大小
			var x = 10 + i * 25;
			var y = randomNum(25, 45);
			var deg = randomNum(-45, 45);
			//修改座標原點和旋轉角度
			ctx.translate(x, y);
			ctx.rotate(deg * Math.PI / 180);
			ctx.fillText(txt, 0, 0);
			//恢復座標原點和旋轉角度
			ctx.rotate(-deg * Math.PI / 180);
			ctx.translate(-x, -y);
		}
		/**繪製干擾線**/
		for (var i = 0; i < 8; i++) {
			ctx.strokeStyle = randomColor(40, 180);
			ctx.beginPath();
			ctx.moveTo(randomNum(0, width), randomNum(0, height));
			ctx.lineTo(randomNum(0, width), randomNum(0, height));
			ctx.stroke();
		}
		/**繪製干擾點**/
		for (var i = 0; i < 100; i++) {
			ctx.fillStyle = randomColor(0, 255);
			ctx.beginPath();
			ctx.arc(randomNum(0, width), randomNum(0, height), 1, 0, 2 * Math.PI);
			ctx.fill();
		}
	}

}


