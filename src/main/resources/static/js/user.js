$(document).ready(function() {
    var token=getToken();
    if(token!=null&&token!=''){
        if($("#login-userid").val()==""){
            $("#token").val(token);
            $("#hidden-form").attr("action","");
            $("#hidden-form").submit();
        }
        $("#manage").click(function () {
            $("#token").val(token);
            $("#hidden-form").attr("action","/manage");
            $("#hidden-form").submit();
        })

    }
});




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
        headers:{
            "Authorization":token,
        },
        async:false,
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
            }else{
                alert(data.message)
            }
        },
        url: "/api/verifyToken",
    })
    return result;
}
$("#logout-btn").click(function () {
    removeToken();
    window.location.reload()
})