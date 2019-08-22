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

$("#form").submit(function () {
    $.ajax({
        async:false,
        data: $("#form").serialize(),
        type: "POST",
        dataType: 'json',
        timeout: 1000,
        cache: false,
        error: function () {
            alert("请求失败");
        },
        success: function (data) {
            if(!data.status){
                alert(data.message)
            }else{
                alert("注册成功")
                saveTokenOnce(data.data);
                console.log("token保存成功");
                window.location.href="/";
            }
        },
        url: "/api/regist",
    })
    return false;
})











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