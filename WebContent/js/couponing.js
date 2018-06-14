$(function(){
    $('header>p').each(function(index){
        $(this).click(function(){
            $('section>div').removeClass('xianshi');
            $('section>div').eq(index).addClass('xianshi');
            $('header>p').removeClass('bor');
            $('header>p').eq(index).addClass('bor');

        })
    })
    coupon();
    $("#used").click(function(){
       used();
    });

    $("#overdue").click(function(){
        overdue();
    });

    $('.tanchu p').click(function(){
        $('#copy').html('')

        $('.tanchu').fadeOut();
    })
})

function toUtf8(str) {
    var out, i, len, c;
    out = "";
    len = str.length;
    for(i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
        }
    }
    return out;
}
function coupon() {
    var openid=$("#openid").val();
    $.ajax({
        async: false,
        type: "GET",
        url:"Couponing",
        data: {
            used : "0",
            openid:openid,
        },
        success: function (data) {
            if (data) {
                var html = "";
                var obj = data.coupons;
                var myDate = new Date();
                var dateString=dateToString(myDate);
                for (var i = 0; i < obj.length; i++) {
                    if(isDateBetween(dateString, obj[i].sdate, obj[i].edate)==true){
                        html += "<div class='box'><div class='yhq_a left'><h2>" +obj[i].couponname+
                            "</h2><p>使用期限：<span>" + obj[i].sdate + "——" + obj[i].edate + "</span></p><div id='pid'>优惠券ID:<a class='copid' id='id' value='"+obj[i].couponno+"'>"+obj[i].couponno+
                            "</a></div><img src='images/dsy.jpg' alt='' class='dsy'/></div>";
                    }
                    }
                $(".xianshi").html(html);
                }
                $(".copid").unbind("click");
                $(".copid").click(function () {
                var copid=$(this).click(".copid").html();
                var str= toUtf8(copid);
                $('#copy').qrcode(str);
                $('.tanchu').show();

            })
        }
    });
}
/**
 * 日期比较大小
 * compareDateString大于dateString，返回1；
 * 等于返回0；
 * compareDateString小于dateString，返回-1
 * @param dateString 日期
 * @param compareDateString 比较的日期
 */
function dateCompare(dateString, compareDateString){
    var dateTime =Date.parse((dateString.replace(/-/g, "/")));
    var compareDateTime =Date.parse((compareDateString.replace(/-/g, "/")));
    if(compareDateTime > dateTime){
        return 1;
    }else if(compareDateTime == dateTime){
        return 0;
    }else{
        return -1;
    }
};

/**
 * 判断日期是否在区间内，在区间内返回true，否返回false
 * @param dateString 日期字符串
 * @param startDateString 区间开始日期字符串
 * @param endDateString 区间结束日期字符串
 * @returns {Number}
 */
function isDateBetween(dateString, startDateString, endDateString){
    var flag = false;
    var startFlag = (dateCompare(dateString, startDateString) < 1);
    var endFlag = (dateCompare(dateString, endDateString) > -1);
    if(startFlag && endFlag){
        flag = true;
    }
    return flag;
};


//将当前时间变成字符串
function dateToString(now){
    var year = now.getFullYear();
    var month =(now.getMonth() + 1).toString();
    var day = (now.getDate()).toString();
    var hour = (now.getHours()).toString();
    var minute = (now.getMinutes()).toString();
    var second = (now.getSeconds()).toString();
    if (month.length == 1) {
        month = "0" + month;
    }
    if (day.length == 1) {
        day = "0" + day;
    }
    if (hour.length == 1) {
        hour = "0" + hour;
    }
    if (minute.length == 1) {
        minute = "0" + minute;
    }
    if (second.length == 1) {
        second = "0" + second;
    }
    var dateTime = year + "-" + month + "-" + day +" "+ hour +":"+minute+":"+second;
    return dateTime;
}
//点击未使用

function used(){
    var openid=$("#openid").val();
    $.ajax({
        async: false,
        type: "GET",
        url: "Couponing",
        data: {
            used : "1",
            openid:openid,
        },
        success: function (data) {
            if (data) {
                var html = "";
                var obj = data.coupons;
                for (var i = 0; i < obj.length; i++) {
                    html += "<div class='box'><div class='yhq_b left'><h2>" +obj[i].couponname+
                            "</h2><p>使用期限：<span>" + obj[i].sdate + "——" + obj[i].edate + "</span></p><div id='pid'>优惠券ID:<a class='copid' id='id' value='"+obj[i].couponno+"'>"+obj[i].couponno+
                            "</a></div></div>";
                    }
                $(".used").html(html);
            }
        }
    });
}

//点击过期
function overdue(){
    var openid=$("#openid").val();
    $.ajax({
        async: false,
        type: "GET",
        url: "Couponing",
        data: {
            used : "0",
            openid:openid,
        },
        success: function (data) {
            if (data) {
                var html = "";
                var obj = data.coupons;
                var myDate = new Date();
                var dateString=dateToString(myDate);
                for (var i = 0; i < obj.length; i++) {
                    if (isDateBetween(dateString, obj[i].sdate, obj[i].edate) == false) {
                        html += "<div class='box'><div class='yhq_c left'><h2>" + obj[i].couponname +
                            "</h2><p id='guoqi'>使用期限：<span >" + obj[i].sdate + "——" + obj[i].edate + "</span></p><div id='pid'>优惠券ID:<a class='copid' id='id' value='" + obj[i].couponno + "'>" + obj[i].couponno +
                            "</a></div></div>";
                    }
                }
                $(".overdue").html(html);
            }
        }
    });

}



