var token;
var list=$("#msglist")
var hiddenid=$("#hiddenreceiverid")
var hiddenname=$("#hiddenreceivername")
$(document).ready(function() {
    token=getToken();
    if(hiddenid.val()!=null&&hiddenid.val()!=""){
        var id=Number(hiddenid.val());
        $(".pm").show();
        $("#receiverid").val(id);
        loadMessage(id,hiddenname.val());
    }else {
        $(".sender").click(function () {
            var id=$(this).attr("userid");
            var name=$(this).attr("username");
            $(".pm").show();
            if (id!='0') {
                loadMessage(Number(id),name);
                $("#noticespan").hide()
                $("#userspan").show()
                $(".psfm").show()
                $("#receiverid").val(id);
                hiddenid.val(id)
                hiddenname.val(name);
            }else {
                loadMessage(0,"")
                $(".psfm").hide();
                $("#noticespan").show()
                $("#userspan").hide()
                hiddenid.val(0)
                hiddenname.val("");
            }
        })
    }
});
var msg="<li class=\"cl\">\n" +
    "                                    <div class=\"pmd\">消息内容</div>\n" +
    "                                </li>\n" +
    "                                <li class=\"cl pmm\">\n" +
    "                                    <div class=\"pmd\">消息内容</div>\n" +
    "                                </li>"


$("#pmform").submit(function () {
    if(!ajaxWithToken($(this).serialize(),token,"/api/message/sendMessage","POST")){
        alert("发送失败")
    }else {
        refreshMessage();
        return false;
    }
    return false;
})

function loadMessage(senderid,sendername) {
    $(".messagespan").remove();
    var message=ajaxWithToken({senderid:senderid},token,"/api/message/getMessages","POST");
    $(".pm").show();
    $("#receiver-name").html(sendername);
    $.each(message,function (i,data) {
        if(data.senderid==senderid){
            list.append("<li class=\"cl messagespan\">" +
                "                                        <div class=\"pmd\">"+data.message+"</div>" +
                "                                    </li>")
        }else{
            list.append("                                <li class=\"cl pmm messagespan\">\n" +
                "                                    <div class=\"pmd\">"+data.message+"</div>\n" +
                "                                </li>")
        }
    })
    $('#msglist').scrollTop( $('#msglist')[0].scrollHeight );
    return false
}

function refreshMessage() {
    $(".messagespan").remove();
    var message=ajaxWithToken({senderid:hiddenid.val(),},token,"/api/message/getMessages","POST");
    $.each(message,function (i,data) {
        if(data.senderid==hiddenid.val()){
            list.append("<li class=\"cl messagespan\">" +
                "                                        <div class=\"pmd \">"+data.message+"</div>" +
                "                                    </li>")
        }else{
            list.append("                                <li class=\"cl pmm messagespan\">\n" +
                "                                    <div class=\"pmd\">"+data.message+"</div>\n" +
                "                                </li>")
        }
    })
    $('#msglist').scrollTop( $('#msglist')[0].scrollHeight );
    return false;
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