<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css">
    <meta charset="UTF-8">
    <title>尚硅谷会员登录页面</title>

    <%@include file="/pages/common/head.jsp" %>

    <script>
        window.onload = function () {
            document.getElementById("img").onclick = function () {
                this.src = "/checkCodeServlet?time=" + new Date().getTime();
            }
        }
    </script>

</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>

                    <a href="/pages/user/regist.jsp"><span>没有账号?</span>去注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        ${msg}
                    </span>
                </div>
                <div class="form">
                    <form action="/UserServlet" method="post">
                        <input type="hidden" name="action" value="login">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password"/>
                        <br/>
                        <br/>
                        <label> 验 证 码 ：</label>
                        <input class="itxt" type="text" name="code" style="width: 150px;" id="code"
                               placeholder="请输入验证码"/>
                        <img id="img" alt="" src="/checkCodeServlet" style="float: right; margin-right: 8px">
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp" %>
>
</body>
</html>