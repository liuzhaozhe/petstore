<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买</title>
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
    <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
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
                                <a href="category?category=BD">&nbsp;鸟&nbsp;</a>
                            </li>
                            <li>
                                <a href="category?category=CA">&nbsp;猫&nbsp;</a>
                            </li>
                            <li>
                                <a href="category?category=DG">&nbsp;狗&nbsp;</a>
                            </li>
                            <li>
                                <a href="category?category=FI">&nbsp;鱼&nbsp;</a>
                            </li>
                            <li>
                                <a href="category?category=RP">爬行动物</a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
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
    <h3>购买</h3>
    <div class="container">
        <form action="addBill" method="post">
            <div class=""></div>
            <div>
                <input type="text" name="billId" value="0" hidden="hidden"/>
                收货人：<input type="text" name="consignee" value="${sessionScope.user.name}"
                           required="required"/>
                电话号码：<input type="text" name="consigneePhone" value="${sessionScope.user.phone}"
                            required="required"/>
                <br/>
                <br/>
                收货地址：<input type="text" name="consigneeAddress" value="${sessionScope.user.address}"
                            required="required"/>
            </div>
        <%--</form>--%>
        <div class="bs-docs-example">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>商品编号</th>
                    <th>商品名称</th>
                    <th>库存</th>
                    <th>商品价格</th>
                    <th>数量</th>
                    <th>总价格</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="body">
                <c:forEach items="${sessionScope.buyList}" var="item">
                    <tr>
                        <td>${item.productId}</td>
                        <td>${item.productName}</td>
                        <td></td>
                        <td><span>${item.price}</span>￥</td>
                        <td>
                            <input type="number" name="amount" value="${item.amount}" min="1"
                                   placeholder="购买数量"/>
                        </td>
                        <td class="row-total-price"><span>${item.totalPrice}</span>￥</td>
                        <td>
                            <button><a href="deleteItem?productId=${item.productId}">删除</a></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            总金额：<span id="allPrice">${requestScope.totalPrice}</span>￥
            <hr/>
            <input type="submit" value="确认账单"/>
        </div>

        </form>
        <!--
        表单不在这里结束，在表格之前结束。因为表格中的数量也会提交，struts2 action 通过模型驱动接受参数，bill类没有amount
        属性，会报错。通过js提交表单
        -->
        <script>
            $(document).ready(function () {
                $("#body").children().each(function (index, element) {
                    // 获取商品ID
                    var productId = $(this).children("td").first();// 获取库存的位置
                    var stock = $(this).children("td:eq(2)");
                    // 获取单价的位置
                    var price = $(this).children("td:eq(3)");
                    // 获取数量位置
                    var amount = price.next();
                    // 获取总价格位置
                    var totalPrice = amount.next();
                    // 当数量变了，更新每行总价格
                    amount.change(function () {
                        var priceNumber = price.children().text();
                        var count = $(this).children().val();
                        var totalPriceNumber = priceNumber * count;
                        totalPrice.children().text(totalPriceNumber);
                        // 更新账单总额
                        updateAllPrice();
                        // 向服务器更改信息
                        $.post(
                                "updateItem",
                                {
                                    productId: productId.text(),
                                    amount: count
                                }
                        );
                    });
                    // 5秒更新一次库存
                    stockFlush();
                    function stockFlush() {
                        $.post(
                                "getStock",
                                {
                                    productId: productId.text()
                                },
                                function (data, status) {
                                    if (status == "success") {
                                        stock.text(data);
                                        amount.children().attr("max", data);
                                    }
                                }
                        );
                        setTimeout(stockFlush, 5000);
                    }
                });
                // 更新账单的总价格
                updateAllPrice();
                // 更新账单的总价格函数
                function updateAllPrice() {
                    var allPrice = 0;
                    $(".row-total-price span").each(function (index, element) {
                        var price = $(this).text();
                        allPrice += parseFloat(price);
                    });
                    $("#allPrice").text(allPrice);
                    // 判断账单是否有商品
                    if (allPrice == 0) {
                        alert("您已删除所有的商品，请重新选择商品。\n您现在不能确认账单。");
                        $(":submit:last").attr("disabled", "disabled");
                        var span = $("<span></span>").text(" 您已删除所有的商品，请重新选择商品。您现在不能确认账单。");
                        span.css("color", "red");
                        $(":submit:last").after(span);
                    }
                }

                $(":submit:last").click(function(){
                    $("form:last").submit();
                });
            });
        </script>
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
