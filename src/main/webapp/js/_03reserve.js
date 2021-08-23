
// $(document).ready(function () {

//     var sideliLength=document.querySelectorAll(".sideLi").length
//     for(i=0;i<sideliLength;i++){
//         alert(document.getElementsByClassName("sideLi")[i].innerHTML)
//     }
//             $(".sideList").on('click',function(){

//                 alert($(this).html())
//             })


//     });
// // $("#Assistive1").on('change', function () {
// //     if ($(this).val() == '2') {
// //         $("#text1").html("輔具類")
// //         $("#text2").html("輪椅")
// //         $("#span1").html(">")
// //         $("#span2").html(">")
// //         $("#commodityname").html("輪椅")
// //         $(".word").html("我是輪椅")
// //     }
// //     else if ($(this).val() == '3') {
// //         $("#text1").html("輔具類")
// //         $("#text2").html("拐杖")
// //         $("#span1").html(">")
// //         $("#span2").html(">")
// //         $("#commodityname").html("拐杖")
// //         $(".word").html("我是拐杖")
// //     }
// // })

// // $("#Assistive2").on('change', function () {
// //     if ($(this).val() == '2') {
// //         $("#text1").html("護具類")
// //         $("#text2").html("護膝")
// //         $("#span1").html(">")
// //         $("#span2").html(">")
// //         $("#commodityname").html("護膝")
// //         $(".word").html("我是護膝")
// //     }
// //     else if ($(this).val() == '3') {
// //         $("#text1").html("護具類")
// //         $("#text2").html("護腰")
// //         $("#span1").html(">")
// //         $("#span2").html(">")
// //         $("#commodityname").html("護腰")
// //         $(".word").html("我是護腰")
// //     }
// // })

// // $("#Assistive3").on('change', function () {
// //     if ($(this).val() == '2') {
// //         $("#text1").html("居家保健")
// //         $("#text2").html("照護病床")
// //         $("#span1").html(">")
// //         $("#span2").html(">")
// //         $("#commodityname").html("照護病床")
// //         $(".word").html("照護病床")
// //     }
// // })

// // $("#Assistive4").on('change', function () {
// //     if ($(this).val() == '2') {
// //         $("#text1").html("優惠活動")
// //         $("#text2").html("")
// //         $("#span1").html(">")
// //         $("#span2").html(">")
// //         $(".word").html("")
// //     }
// //     else if ($(this).val() == '3') {
// //         $("#text1").html("優惠活動")
// //         $("#text2").html("護腰")
// //         $("#span1").html(">")
// //         $("#span2").html(">")
// //         $("#commodityname").html("")
// //     }
// // })




// 留言板
i = 1;
function fnsubmit() {
    var odiv = document.getElementById("box");
    var oem = odiv.getElementsByTagName("em")[0];
    var otext = document.getElementById("text");
    var oli = odiv.getElementsByTagName("li");
    var add_li = document.createElement("li");
    var o_span = document.createElement("span");
    if (otext.value == "") {
        alert("請填寫留言内容！");
        return;
    }
    oem.style.display = "none";
    o_span.style.position = "absolute";
    o_span.style.top = "-20px";
    o_span.style.right = "20px";
    o_span.style.background = "#cccccc";
    add_li.style.position = "relative";
    add_li.index = i;
    add_li.style.background = "#cccccc";
    add_li.style.marginBottom = "20px";
    var str = document.createTextNode(i + "、" + otext.value);
    var strspan = document.createTextNode("確定刪除" + i + "、" + otext.value + "内容？");
    add_li.appendChild(o_span);
    o_span.style.display = "none";
    o_span.appendChild(strspan);
    add_li.appendChild(str);
    odiv.appendChild(add_li);
    i++;
    for (j = 0; j < oli.length; j++) {
        var m = j;
        oli[j].onmouseover = function () {
            this.style.background = "#ffff00";
            (this.childNodes(o_span)).style.display = "block";
        };
        oli[j].onmouseout = function () {
            this.style.background = "#cccccc";
            (this.childNodes(o_span)).style.display = "none";
        };
        oli[j].onclick = function () {
            m--;
            odiv.removeChild(this);
            if (m < 0) {
                oem.style.display = "block";
            };
        };
    };
}