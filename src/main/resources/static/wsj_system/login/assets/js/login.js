/**
 * Created by SkyLin on 2017/6/26.
 */
/*/wsj-spring/staff/login.do*/
<!--script-->
$(function () {
    $("#loginBtn").click(function () {
        var param = {
            loginName: $('#account').val(),
            password: $('#password').val(),
            autoLogin: true
        };
        query(
            '/wsj/login/login.do',
            param,
            function (data) {
                if (data.success) {
                    query('/wsj/login/index.do', {}, function () {
                    }, function () {
                    });
                } else {
                    alert("登陆失败");
                }
            },
            function () {
                console.log('失败，回到登陆页面');
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


