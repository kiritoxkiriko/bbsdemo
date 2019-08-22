$(document).ready(function() {
    var token=getToken();
    if(token!=null&&token!=''){
        if(verifyToken(token)){
            console.log('认证成功');
            $("#fastpost-cover").fadeOut();
            $(".not-login").hide();
            $(".has-login").show();
            var data=ajaxWithToken({},token,"/api/user/my","POST");
            if(data!=null){
                $("#my-space").html(data.username+"的个人主页")
                $("#my-space").attr("href","/user/"+data.userid);
            }
            $("#my-msg").attr("href","/message?token="+token)
            var count=ajaxWithToken({},token,"/api/message/unreadMessage","POST")
            if (count!=null){
                if(count>0){
                    $("#my-msg span").html("["+count+"]")
                }
            }
            $(".delete-post a").click(function () {
                if(!confirm("确认删除吗")){
                    return
                }
                    var postid = $(this).attr('postid')
                    var boardid = $(this).attr('boardid')
                    var data = {boardid: boardid, postid: postid}
                    if (ajaxWithToken(data, token, "/api/manage/deletePost", "POST")) {
                        alert("删除成功");
                        window.location.reload()
                    }

            })
        }else {
            $(".delete-post").remove();
            console.log('认证失败');
        }
    }else {
        $(".delete-post").remove();
    }
});
$("#search_btn").click(function () {
    $("#search_form").submit()
})
$(".delete-post").click(function () {
    var id=$(this).attr("postid")

})


$("#postform").submit(function () {
    if($("#name").val()==""||$("#name").val()==null){
        alert("标题不能为空")
        return
    }
    console.log("请求已拦截");
    var token=getToken()
    $.ajax({
        async:false,
        headers:{
            "Authorization":token,
        },
        data: $("#postform").serialize(),
        type: "POST",
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
                if(!data.data){
                    alert("发送失败");
                }else{
                    alert("发送成功");
                    window.location.reload()
                }
            }
        },
        url: "/api/post/doPost",
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
        async:false,
        headers:{
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