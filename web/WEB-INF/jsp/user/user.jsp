<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords"
          content="Play-Offs Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!meta charset utf="8">
    <!--bootstrap-->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!--coustom css-->
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/my-style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-ui.min.css">
    <!--script-->
    <script src="js/jquery-1.12.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <!--fonts-->
    <link href='http://fonts.googleapis.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <!--script-->
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 900);
            });
        });
    </script>
</head>
<body>
<!--header-part-->
<div class="banner-background" id="to-top">
    <div class="container">
        <div class="nav-back">
            <div class="navigation">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a class="active" href="index.jsp">&nbsp;&nbsp;主页&nbsp;&nbsp;<span class="sr-only">(current)</span></a>
                            </li>
                            <li><a href="category?category=BD">&nbsp;鸟&nbsp;</a></li>
                            <li><a href="category?category=CA">&nbsp;猫&nbsp;</a></li>
                            <li><a href="category?category=DG">&nbsp;狗&nbsp;</a></li>
                            <li><a href="category?category=FI">&nbsp;鱼&nbsp;</a></li>
                            <li><a href="category?category=RP">爬行动物</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                    <div class="clearfix"></div>
                    <div class="clearfix"></div>
                </nav>
                <div class="clearfix"></div>
            </div>
            <div class="logo">
                <h1><a href="index.jsp">PET<span class="hlf"> STORE</span></a></h1>
            </div>
            <div class="account">
                <span>
                    <a class="account" href="shoppingCar"><img src="images/car.png" alt="购物车"/></a>
                    <%--判断是否登录--%>
                    <c:if test="${sessionScope.user == null}">
                        <a href="loginForm">登录</a>
                        <a href="signForm">注册</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <a href="userInfo">${sessionScope.user.username}</a>
                        <a href="signOut">登出</a>
                    </c:if>
                </span>
            </div>
            <div class="ui-widget search">
                <form action="search" method="post">
                    <input type="text" name="search" id="autocomplete" autocomplete="off" placeholder="商品名称"
                           required="required"/>
                    <input type="submit" value="查询"/>
                    <script>
                        $(document).ready(function () {
                            $("#autocomplete").autocomplete({
                                source: function (request, response) {
                                    $.ajax({
                                        type: "POST",
                                        url: "matchName",
                                        data: "search=" + $("#autocomplete").val(),
                                        dataType: "json",
                                        success: function (jsonObj) {
                                            response(jsonObj);
                                        }
                                    });
                                },
                                minLength: 1,
                                select: function (event, ui) {
                                    $("#autocomplete").val(ui.item.value);
                                    $(":submit:first").click();
                                }
                            });
                        });
                    </script>
                </form>
            </div>
        </div>
    </div>
</div>
<!--header-ends-->
<!--pages-->
<div class="typography">
    <h3>用户信息</h3>
    <div class="container">
        <div class="user-box">
            <s:form action="updateUser">
                <s:actionerror/>
                <s:actionmessage/>
                <s:textfield name="username" label="用户名" value="%{#session.user.username}" readonly="true"/>
                <s:password name="password" label="密码"/>
                <s:password name="password2" label="确认密码"/>
                <s:textfield name="name" label="姓名" value="%{#session.user.name}"/>
                <s:textfield name="address" label="地址" value="%{#session.user.address}"/>
                <s:textfield name="phone" label="电话号码" value="%{#session.user.phone}"/>
                <s:textfield name="email" label="邮箱" value="%{#session.user.email}"/>
                <s:textfield name="birthday" label="出生日期">
                    <s:param name="value"><s:date name="%{#session.user.birthday}" format="yyyy-MM-dd"/></s:param>
                </s:textfield>
                <s:select list="{'鸟','猫','狗','鱼','爬行动物'}" label="喜欢的种类" name="favcategory"
                          value="%{#session.user.favcategory}"/>
                <s:radio list="#{1:'是',0:'否'}" label="是否显示图案" name="banneropt" value="%{#session.user.banneropt}"/>
                <s:submit value="修改" cssClass="btn btn-danger"/>
            </s:form>
            <a href="billList">查看账单</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="log">查看操作日志</a>
        </div>

        <%--<div class="user-box">--%>
            <%--<c:if test="${requestScope.msg != null}">--%>
                <%--<div class="user-error">${requestScope.msg}</div>--%>
            <%--</c:if>--%>
            <%--<form action="updateUser" method="post">--%>
                <%--<table>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--用户名：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="username" required="required" value="${sessionScope.user.username}"--%>
                                   <%--readonly="readonly"/><span id="checkUsername"></span>--%>
                            <%--用户名(只能是字母和数字的组合)--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--密码：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="password" name="password"/>--%>
                            <%--不用修改密码不用填--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--确认密码：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="password" name="password2"/><span id="checkPassword"></span>--%>
                            <%--不用修改密码不用填--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--姓名：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="name" value="${sessionScope.user.name}"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--地址：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="address" value="${sessionScope.user.address}"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--电话号码：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="phone" value="${sessionScope.user.phone}"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--邮箱：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="email" name="email" value="${sessionScope.user.email}"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--出生日期：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="birthday"--%>
                                   <%--value="<fmt:formatDate value="${sessionScope.user.birthday}" pattern="yyyy-MM-dd"/>"/>--%>
                            <%--格式：2016-02-03--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--喜欢的种类：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<select name="favcategory">--%>
                                <%--<c:if test="${sessionScope.user.favcategory == '鸟'}">--%>
                                    <%--<option value="鸟" selected="selected">鸟</option>--%>
                                    <%--<option value="猫">猫</option>--%>
                                    <%--<option value="狗">狗</option>--%>
                                    <%--<option value="鱼">鱼</option>--%>
                                    <%--<option value="爬行动物">爬行动物</option>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${sessionScope.user.favcategory == '猫'}">--%>
                                    <%--<option value="鸟">鸟</option>--%>
                                    <%--<option value="猫" selected="selected">猫</option>--%>
                                    <%--<option value="狗">狗</option>--%>
                                    <%--<option value="鱼">鱼</option>--%>
                                    <%--<option value="爬行动物">爬行动物</option>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${sessionScope.user.favcategory == '狗'}">--%>
                                    <%--<option value="鸟">鸟</option>--%>
                                    <%--<option value="猫">猫</option>--%>
                                    <%--<option value="狗" selected="selected">狗</option>--%>
                                    <%--<option value="鱼">鱼</option>--%>
                                    <%--<option value="爬行动物">爬行动物</option>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${sessionScope.user.favcategory == '鱼'}">--%>
                                    <%--<option value="鸟">鸟</option>--%>
                                    <%--<option value="猫">猫</option>--%>
                                    <%--<option value="狗">狗</option>--%>
                                    <%--<option value="鱼" selected="selected">鱼</option>--%>
                                    <%--<option value="爬行动物">爬行动物</option>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${sessionScope.user.favcategory == '爬行动物'}">--%>
                                    <%--<option value="鸟">鸟</option>--%>
                                    <%--<option value="猫">猫</option>--%>
                                    <%--<option value="狗">狗</option>--%>
                                    <%--<option value="鱼">鱼</option>--%>
                                    <%--<option value="爬行动物" selected="selected">爬行动物</option>--%>
                                <%--</c:if>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--是否显示图案：--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<c:if test="${sessionScope.user.banneropt == 1}">--%>
                                <%--<input type="radio" name="banneropt" value="1" checked="checked"/>是--%>
                                <%--<input type="radio" name="banneropt" value="0"/>否--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${sessionScope.user.banneropt == 0}">--%>
                                <%--<input type="radio" name="banneropt" value="1"/>是--%>
                                <%--<input type="radio" name="banneropt" value="0" checked="checked"/>否--%>
                            <%--</c:if>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td colspan="2">--%>
                            <%--<button type="submit" class="btn btn-danger">修改</button>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
                <%--<a href="billList">查看账单</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
                <%--<a href="log">查看操作日志</a>--%>
            <%--</form>--%>
            <%--<script>--%>
                <%--$(document).ready(function () {--%>
                    <%--var passwordInput = $(":password");--%>
                    <%--var password1Input = passwordInput.first();--%>
                    <%--var password2Input = passwordInput.last();--%>
                    <%--password2Input.change(function () {--%>
                        <%--var password1 = password1Input.val();--%>
                        <%--var password2 = password2Input.val();--%>
                        <%--if (password1 == "") {--%>
                            <%--return;--%>
                        <%--} else {--%>
                            <%--if (password1 != password2) {--%>
                                <%--alert("两次输入的密码不相等");--%>
                                <%--$("#checkPassword").empty();--%>
                                <%--$("#checkPassword").append("<img src=\"images/error.png\">");--%>
                            <%--} else {--%>
                                <%--$("#checkPassword").empty();--%>
                                <%--$("#checkPassword").append("<img src=\"images/success.png\">");--%>
                            <%--}--%>
                        <%--}--%>
                    <%--});--%>
                <%--});--%>
            <%--</script>--%>
        <%--</div>--%>
    </div>
    <!--container-->
</div>
<!--pages-->
<!--footer-->
<div class="footer">
    <div class="container">
        <div class="col-md-6 mrg1">
            <div class="col-md-4 brk4">
                <div class="about">
                    <h4>ABOUT</h4>
                    <ul>
                        <li><a href="https://github.com/hezhujun/petstore/">github.com</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 brk4">
                <c:if test="${sessionScope.user.banneropt == 1}">
                    <c:if test="${sessionScope.user.favcategory == '鸟'}">
                        <img src="images/BD.png"/>
                    </c:if>
                    <c:if test="${sessionScope.user.favcategory == '猫'}">
                        <img src="images/CA.png"/>
                    </c:if>
                    <c:if test="${sessionScope.user.favcategory == '狗'}">
                        <img src="images/DG.png"/>
                    </c:if>
                    <c:if test="${sessionScope.user.favcategory == '鱼'}">
                        <img src="images/FI.png"/>
                    </c:if>
                    <c:if test="${sessionScope.user.favcategory == '爬行动物'}">
                        <img src="images/RP.png"/>
                    </c:if>
                </c:if>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!--footer-->
<!---->
<script type="text/javascript">
    $(document).ready(function () {
        /*
         var defaults = {
         containerID: 'toTop', // fading element id
         containerHoverID: 'toTopHover', // fading element hover id
         scrollSpeed: 1200,
         easingType: 'linear'
         };
         */
        $().UItoTop({easingType: 'easeOutQuart'});
    });
</script>
<a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!---->
</body>
</html>
