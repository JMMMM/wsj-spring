/**
 * Created by SkyLin on 2017/6/26.
 */
/*/wsj-spring/staff/login.do*/
<!--script-->
$(function () {
    $("#loginBtn").click(function () {
        var param = {
            loginName: $('#loginName').val(),
            password: $('#password').val(),
            autoLogin: false
        };
        query(
            '/login/login.do',
            param,
            function (data) {
                if (data.success) {
                    $('#errorMsg').hide();
                    document.location.href = "/login/index.do";
                } else {
                    $('#errorMsg').show();
                }
            },
            function () {
                console.log('回到登陆页面');
            });
    });
    $("#password").keydown(function (key) {
        if (key.keyCode == 13) {
            $("#loginBtn").click();
        }
    });

    function query(url, param, success, error) {
        url = window.document.location.origin + url;
        var realParam = $.extend({}, param);
        $.ajax({
            url: url,
            type: "GET",
            data: realParam,
            dataType: "json",
            success: function (data) {
                success(data);
            },
            error: function (XMLHttpRequest) {
                error(XMLHttpRequest);
            }
        });
    }
});

