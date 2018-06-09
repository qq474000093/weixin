


//单击输入框的默认样式(获得焦点的样式设置)
$(function() {
    //聚焦失焦input
    $('input').eq(0).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("手机号不能为空");
            $('input').eq(1).parent().next("div").text("");
            $('input').eq(6).parent().next("div").text("");
            $('input').eq(7).parent().next("div").text("");
        }
    });
    $('input').eq(1).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("验证码不能为空");
            $('input').eq(0).parent().next("div").text("");
            $('input').eq(6).parent().next("div").text("");
            $('input').eq(7).parent().next("div").text("");
        }
    });
    $('input').eq(2).focus(function () {
        $('input').eq(0).parent().next("div").text("");
        $('input').eq(1).parent().next("div").text("");
        $('input').eq(6).parent().next("div").text("");
        $('input').eq(7).parent().next("div").text("");
    });
    $('input').eq(3).focus(function () {
        $('input').eq(0).parent().next("div").text("");
        $('input').eq(1).parent().next("div").text("");
        $('input').eq(6).parent().next("div").text("");
        $('input').eq(7).parent().next("div").text("");
    });
    $('input').eq(4).focus(function () {
        $('input').eq(0).parent().next("div").text("");
        $('input').eq(1).parent().next("div").text("");
        $('input').eq(6).parent().next("div").text("");
        $('input').eq(7).parent().next("div").text("");
    });
    $('input').eq(5).focus(function () {
        $('input').eq(0).parent().next("div").text("");
        $('input').eq(1).parent().next("div").text("");
        $('input').eq(6).parent().next("div").text("");
        $('input').eq(7).parent().next("div").text("");
    });
    $('input').eq(6).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("按正确的方式输入");
            $('input').eq(0).parent().next("div").text("");
            $('input').eq(1).parent().next("div").text("");
            $('input').eq(7).parent().next("div").text("");
        }
    });
    $('input').eq(7).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("与上面输入的一致");
            $('input').eq(0).parent().next("div").text("");
            $('input').eq(1).parent().next("div").text("");
            $('input').eq(6).parent().next("div").text("");
        }
    });
    $('input').eq(8).focus(function () {
        $('input').eq(0).parent().next("div").text("");
        $('input').eq(1).parent().next("div").text("");
        $('input').eq(6).parent().next("div").text("");
        $('input').eq(7).parent().next("div").text("");
    });
});





