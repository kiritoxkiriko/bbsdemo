var token;
$(document).ready(function() {
    token=getToken();
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
            $(".deletepost").click(function () {
                if(!confirm("确认删除吗")){
                    return
                }
                var replyid=$(this).attr('replyid')
                if(ajaxWithToken({replyid:replyid},token,"/api/manage/deleteReply","POST")){
                    alert("删除成功");
                    window.location.reload()
                }
            })
            $(".editreply").click(function () {
                var replyid=$(this).attr('replyid');
                if(ajaxWithToken({replyid:replyid},token,"/api/manage/hasRightEditReply","POST")!=null){
                    $("#postReply_"+replyid+" .editarea").show();
                    $("#postReply_"+replyid+" .t_fsz").hide();
                    var innertext=$("#postReply_"+replyid+" .replytext").html();
                    $("#postReply_"+replyid+" .editarea").val(innertext);
                    $("#postReply_"+replyid+" .editreply_fin").show();
                    $(this).hide()
                }
            })
            $(".editreply_fin").click(function () {
                var replyid=$(this).attr('replyid');
                var content=$("#postReply_"+replyid+" .editarea").val();
                if(ajaxWithToken({replyid:replyid,content:content},token,"/api/manage/editReply","POST")){
                    alert("编辑成功")
                    window.location.reload()
                }
            })
        }else {
            $(".deletepost").remove();
            $(".editreply").remove();
            console.log('认证失败');
        }
    }else {
        $(".editreply").remove();
        $(".deletepost").remove();
    }
});
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


$("#fastpostform").submit(function () {
    console.log("请求已拦截");
    var token=getToken()
    $.ajax({
        async:false,
        headers:{
            "Authorization":token,
        },
        data: $("#fastpostform").serialize(),
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
        url: "/api/postReply/doReply",
    })
    return false;
})

$("#search_btn").click(function () {
    $("#search_form").submit()
})
$(".sendmsg_btn").click(function () {
    window.location.href="/message?token="+token+"&receiverid="+$(this).attr("userid")
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