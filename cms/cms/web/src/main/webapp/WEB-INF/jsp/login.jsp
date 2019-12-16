<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>星厨后台管理</title>
    <meta name="description" content="星厨后台管理框架">
    <link type="text/css" href="../static/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="../static/css/font-awesome.min.css" rel="stylesheet"/>
    <link type="text/css" href="../static/css/style.css" rel="stylesheet"/>
    <link type="text/css" href="../static/css/login.min.css" rel="stylesheet"/>
    <link type="text/css" href="../static/ruoyi/css/ry-ui.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link rel="shortcut icon" href="../static/favicon.ico"/>
    <style type="text/css">label.error {
        position: inherit;
    }</style>
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location
        }
        ;
    </script>

</head>
<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1><img alt="[ 若依 ]" src="../static/ruoyi.png"></h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>若依 后台管理系统</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> SpringBoot</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Mybatis</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Shiro</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Thymeleaf</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Bootstrap</li>
                </ul>
                <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
            </div>
        </div>
        <div class="col-sm-5">
            <form id="signupForm">
                <h4 class="no-margins">登录：</h4>
                <input type="text" name="username" class="form-control uname" placeholder="用户名" value="admin"/>
                <input type="password" name="password" class="form-control pword" placeholder="密码" value="admin123"/>
                <div class="row m-t" th:if="${captchaEnabled==true}">
                    <div class="col-xs-6">
                        <input type="text" name="validateCode" class="form-control code" placeholder="验证码" maxlength="5"
                               autocomplete="off">
                    </div>
                    <div class="col-xs-6">
                        <a href="javascript:void(0);" title="点击更换验证码">
                            <img src="captcha/captchaImage?type=${captchaType} " class="imgcode" width="85%"/>
                        </a>
                    </div>
                </div>
                <div class="checkbox-custom" th:classappend="${captchaEnabled==false} ? 'm-t'">
                    <input type="checkbox" id="rememberme" name="rememberme"> <label for="rememberme">记住我</label>
                </div>
                <button class="btn btn-success btn-block" id="btnSubmit"  data-loading="正在验证登录，请稍后...">
                    登录
                </button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2019 All Rights Reserved. RuoYi <br>
        </div>
    </div>
</div>

</body>
</html>
