var userId=null;
$(document).ready(function() {
    var token=getToken();
    if(token!=null&&token!=''){
        if(verifyToken(token)){
            console.log('认证成功')
            window.location.href="/";
        }
    }
});
var password = $('#password');
var username = $('#username');
var loginBtn = $("#login-btn");
var form = $("#loginform")
var messageBox1 = $("#message1");
var messageBox2 = $("#message2");

var usernReg = /(?=.*[a-zA-Z])^[a-zA-Z0-9]/
var psdReg = /(?=.*[a-z])(?=.*[0-9])/

var usernOk = false;
var psdOk = false;
/*username.change(function () {
    messageBox1.html("")
    if (username.val().length >= 4 && username.val().length <= 16) {
        if ($(this).val().match(usernReg)) {
            usernOk = true;
            messageBox1.html("");
        } else {
            messageBox1.html("用户名必须包含字母")
            usernOk = true;
        }
    } else {
        messageBox1.html("用户名长度必须在4-16之间")
        usernOk = true;
    }
})*/
/*password.change(function () {
    if ($(this).val().match(psdReg)) {
        loginBtn.attr("disable", true);
        messageBox2.html("");
        psdOk = true;
    } else {
        messageBox2.html("密码必须包含小写字母和数字");
        psdOk = false;
    }
})*/


form.submit(function () {
    // if (!psdOk || !usernOk || password.val() == '' || username.val() == '') {
    //     alert("输入有误");
    //     return false;
    // }
    $.ajax({
        data: form.serialize(),
        type: "POST",
        dataType: 'json',
        timeout: 1000,
        cache: false,
        error: function () {
            alert("请求失败");
        },
        success: function (data) {
            var result = responeData(data);
            if (result!=null) {
                saveTokenOnce(result);
                console.log("token保存成功");
                window.location.href="/";
            }else{
                console.log("登录失败");
            }
        },
        url: "/api/login",
    })
    return false;
})

function responeData(data) {
    var code = data.code;
    var msg = data.message;
    if (data.status) {
        return data.data;
    } else {
        if (code == 3002) {
            messageBox1.html(msg);
        } else if (code == 3001) {
            messageBox2.html(msg);
        } else {
            alert(msg);
        }
        return null;
    }
}

function saveTokenOnce(token) {
    Cookies.set('token', token);
}

function saveToken(token, days) {
    Cookies.set('token', token, {expires: days});
}

function getToken() {
    return Cookies.get('token');
}

function removeToken() {
    Cookies.remove('token')
}

function ajaxWithToken(data,token,url,method) {
    var result=null;
    $.ajax({
        head:{
            "Authorization":token,
        },
        data: data,
        type: method,
        dataType: 'json',
        timeout: 1000,
        cache: false,
        error: function () {
            alert("请求失败");
        },
        success: function (data) {
            if(data.code!=200){
                alert(data.message)
            }else{
                result=data.data
            }
        },
        url: url,
    })
    return result
}
function verifyToken(token) {
    var result=false;
    $.ajax({
        async:false,
        data: {token:token},
        type: "GET",
        dataType: 'json',
        timeout: 1000,
        cache: false,
        error: function () {
            alert("请求失败");
        },
        success: function (data) {
            console.log('请求成功')
            if(data.status==true){
                result=data.data;
            }else if(data.code==7777){
                removeToken();
            }
            else{
                alert(data.message)
            }
        },
        url: "/api/verifyToken",
    })
    return result;
}
 
 