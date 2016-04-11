<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>类别</title>
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
                            <li>
                                <a href="index.jsp">&nbsp;&nbsp;主页&nbsp;&nbsp;<span class="sr-only">(current)</span></a>
                            </li>
                            <li>
                                <c:if test="${sessionScope.categoryName == 'BD'}">
                                    <a class="active" href="category?category=BD">&nbsp;鸟&nbsp;</a>
                                </c:if>
                                <c:if test="${sessionScope.categoryName != 'BD'}">
                                    <a href="category?category=BD">&nbsp;鸟&nbsp;</a>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${sessionScope.categoryName == 'CA'}">
                                    <a class="active" href="category?category=CA">&nbsp;猫&nbsp;</a>
                                </c:if>
                                <c:if test="${sessionScope.categoryName != 'CA'}">
                                    <a href="category?category=CA">&nbsp;猫&nbsp;</a>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${sessionScope.categoryName == 'DG'}">
                                    <a class="active" href="category?category=DG">&nbsp;狗&nbsp;</a>
                                </c:if>
                                <c:if test="${sessionScope.categoryName != 'DG'}">
                                    <a href="category?category=DG">&nbsp;狗&nbsp;</a>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${sessionScope.categoryName == 'FI'}">
                                    <a class="active" href="category?category=FI">&nbsp;鱼&nbsp;</a>
                                </c:if>
                                <c:if test="${sessionScope.categoryName != 'FI'}">
                                    <a href="category?category=FI">&nbsp;鱼&nbsp;</a>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${sessionScope.categoryName == 'RP'}">
                                    <a class="active" href="category?category=RP">爬行动物</a>
                                </c:if>
                                <c:if test="${sessionScope.categoryName != 'RP'}">
                                    <a href="category?category=RP">爬行动物</a>
                                </c:if>
                            </li>
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
                </form>
            </div>
        </div>
    </div>
</div>
<!--header-ends-->
<!--pages-->
<div class="typography">
    <h3>类别</h3>
    <div class="container">
        <c:forEach items="${sessionScope.category2.keySet()}" var="category2">
            <div class="show">
                <a href="productList?category2=${category2}">
                    <img src="images/${category2}.gif" alt="${sessionScope.category2.get(category2)}"/>
                    <div>${sessionScope.category2.get(category2)}</div>
                </a>
            </div>
        </c:forEach>
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
