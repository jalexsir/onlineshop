<%--
  Created by IntelliJ IDEA.
  User: Alexander Sirenko
  Date: 25.09.2019
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
    <title>Gretong a Ecommerce Category Flat Bootstarp Responsive Website Template | Home :: w3layouts</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css" rel='stylesheet'
          type='text/css'/>
    <!-- jQuery (necessary JavaScript plugins) -->
    <script type='text/javascript'
            src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
    <!-- Custom Theme files -->
    <link href="${pageContext.servletContext.contextPath}/resources/css/style.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Gretong Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Playfair+Display:400,700,900' rel='stylesheet' type='text/css'>
    <!-- start menu -->
    <link href="${pageContext.servletContext.contextPath}/resources/css/megamenu.css" rel="stylesheet" type="text/css"
          media="all"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/megamenu.js"></script>
    <script>$(document).ready(function () {
        $(".megamenu").megamenu();
    });</script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/menu_jquery.js"></script>
    <%--<script src="${pageContext.servletContext.contextPath}/resources/js/simpleCart.min.js"> </script>--%>
</head>
<body>
<!-- header_top -->
<div class="top_bg">
    <div class="container">
        <div class="header_top">
            <div class="top_right">
                <ul>
                    <li><label>
                        <c:choose>
                            <c:when test="${elogin!=null}">
                                <b>${name}</b>
                            </c:when>
                            <c:otherwise>
                                Guest
                            </c:otherwise>
                        </c:choose>
                    </label></li>
                    |
                    <li><a href="">Contact</a></li>
                    |
                    <li><a href="">Delivery information</a></li>
                </ul>
            </div>
            <div class="top_left">
                <h2><span></span> Call us : 095 4020 357</h2>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- header -->
<div class="header_bg">
    <div class="container">
        <div class="header">
            <div class="head-t">
                <div class="logo">
                    <a href="${pageContext.servletContext.contextPath}/"><img
                            src="${pageContext.servletContext.contextPath}/resources/images/logo.png"
                            class="img-responsive" alt=""/> </a>
                </div>
                <!-- start header_right -->
                <div class="header_right">
                    <div class="rgt-bottom">
                        <c:choose>
                            <c:when test="${elogin!=null}">
                                <div class="log">
                                    <div class="login">
                                        <div id="loginContainer"><a
                                                href="${pageContext.servletContext.contextPath}/profile" id=""><span>Profile</span></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="log">
                                    <form id="logoutForm" action="${pageContext.servletContext.contextPath}/"
                                          method="get">
                                        <button type="submit" id="logout" value="ok" name="logout"><span>LOGOUT</span>
                                        </button>
                                    </form>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="log">
                                    <div class="login">
                                        <div id="loginContainer"><a href="#" id="loginButton"><span>Login</span></a>
                                            <div id="loginBox">
                                                <form id="loginForm"
                                                      action="${pageContext.servletContext.contextPath}/registration"
                                                      method="post">
                                                    <fieldset id="body">
                                                        <fieldset>
                                                            <label for="email">Email Address</label>
                                                            <input type="text" name="login" id="email"
                                                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                                                   title="Login should contain e-mail address"
                                                                   required placeholder="Enter e-mail"
                                                                   value="${login}">
                                                        </fieldset>
                                                        <fieldset>
                                                            <label for="password">Password</label>
                                                            <input type="password" name="password" id="password">
                                                        </fieldset>
                                                        <center>
                                                            <div class="layer">${enteringError}</div>
                                                        </center>
                                                        <br>
                                                        <input type="hidden" name="key" value="auth">
                                                        <input type="submit" id="login" value="Sign in">
                                                        <label for="checkbox"><input type="checkbox" id="checkbox"> <i>Remember
                                                            me</i></label>
                                                    </fieldset>
                                                    <span><a
                                                            href="${pageContext.servletContext.contextPath}/registration">Forgot your password?</a></span>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="reg">
                                    <a href="${pageContext.servletContext.contextPath}/registration">REGISTER</a>
                                </div>
                            </c:otherwise>
                        </c:choose>


                        <div class="cart box_1">
                            <a href="${pageContext.servletContext.contextPath}/cart">
                                <h3><span class="simpleCart_total"></span> (<span id="simpleCart_quantity"
                                                                                  class="simpleCart_quantity">${cartSize!=null?cartSize:0}</span>
                                    items)<img src="${pageContext.servletContext.contextPath}/resources/images/bag.png"
                                               alt=""></h3>
                            </a>
                            <p><a href="" class="simpleCart_empty"></a></p>
                            <div class="clearfix"></div>
                        </div>

                        <div class="create_btn">
                            <a href="${pageContext.servletContext.contextPath}/cart">CHECKOUT</a>
                        </div>
                        <div class="clearfix"></div>

                    </div>
                    <div class="search">
                        <form>
                            <input type="text" value="" placeholder="search...">
                            <input type="submit" value="">
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
            </div>
            <!-- start header menu -->
            <ul class="megamenu skyblue">
                <li class="active grid"><a class="color1" href="${pageContext.servletContext.contextPath}/">Home</a>
                </li>
                <li class="grid"><a class="color2" href="${pageContext.servletContext.contextPath}/product">ALL
                    PRODUCTS</a>

                <li><a class="color4" href="#">TUXEDO</a>

                <li><a class="color5" href="#">SWEATER</a>

                <li><a class="color6" href="${pageContext.servletContext.contextPath}/product/category/3">SHOES</a>

                <li><a class="color7" href="${pageContext.servletContext.contextPath}/product/category/2">GLASSES</a>

                <li><a class="color8" href="#">T-SHIRT</a>

                <li><a class="color9" href="${pageContext.servletContext.contextPath}/product/category/1">WATCHES</a>

                </li>
            </ul>
        </div>
    </div>
</div>