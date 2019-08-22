/*$(document).ready(function () {
    load();
})
var token=getToken();
var level=Number($("#level").val())
var loginUserId=Number($("#login-userid").val())

var postList="<tr class=\"odd\" role=\"row\" style=\"height:30px\" id='123'>\n" +
    "                                                <td class=\"sorting_1\">帖名</td>\n" +
    "                                                <td>用户</td>\n" +
    "                                                <td><a href=\"javascript:void(0);\" style=\"color:black\">删除</a></td>\n" +
    "                                            </tr>"
var boardList="<tr class=\"odd\" role=\"row\" style=\"height:30px\">\n" +
    "                                                <td class=\"sorting_1\">板块编号</td>\n" +
    "                                                <td>板块名</td>\n" +
    "                                                <td><a href=\"javascript:void(0);\" style=\"color:black\">删除</a></td>\n" +
    "                                                <td><a href=\"javascript:void(0);\" style=\"color:black\">修改</a></td>\n" +
    "                                            </tr>"

var postManage=$("#post-manage")
var boardManage=$("#board-manage")
var userManage=$("#user-manage")
var moderatorManage=$("#moderator-manage")
function load() {
    if (level == 0) {
        $(".posts").remove();
        console.log("移除成功")
        var posts = ajaxWithToken({ userid: loginUserId},token,"/api/post/getUserPost","POST")
        console.log("请求成功")
        $.each(posts, function (i, data) {
            console.log("添加postid:"+data.postid)
            $("#post-tfboy").append("<tr class=\\\"posts\\\" role=\\\"row\\\" style=\\\"height:30px\\\" id=post-" + data.postid + ">\\n\" +\n" +
                "    \"                                                <td class=\\\"sorting_1\\\">"+data.name+"</td>\\n\" +\n" +
                "    \"                                                <td>" + data.postbyId + "</td>\\n\" +\n" +
                "    \"                                                <td><a href='javascript:void(0);' boardid='"+data.boardid+"'  postid='"+data.postid+"' class='deletepost' style=\\\"color:black\\\">删除</a></td>\\n\" +\n" +
                "    \"                                            </tr>")
            console.log("添加成功")

        })
        console.log("遍历成功")

        $(".deletepost").on("click",function () {
            var postid = $(this).attr("postid")
            var boardid = $(this).attr("boardid")
            //alert(id);
            if (ajaxWithToken({postid: postid, boardid: boardid}, token, "/api/manage/deletePost","POST")) {
                alert("删除成功")
            } else {
                alert("删除失败")
            }
        })
        console.log("添加事件成功")
    }else if(level==3){

    }
}





*/


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