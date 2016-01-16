<!DOCTYPE html>
<%@tag description="General Template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header_area" fragment="true" %>
<%@attribute name="content_area" fragment="true" %>
<%@attribute name="footer_area" fragment="true" %>
<%@attribute name="content_area_login" fragment="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>PhotoContest - ${title}</title>
    <jsp:invoke fragment="head"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link href="<spring:url value='/resources/css/styles.css'/>" rel="stylesheet" />
    <link href="<spring:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet" />
    <script type="text/javascript" src="<spring:url value='/resources/js/jquery-2.1.4.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/resources/js/main-script.js'/>"></script>
    <!-- Internet Explorer HTML5 enabling code: -->

    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>

    <style type="text/css">
        .clear {
            zoom: 1;
            display: block;
        }
    </style>

    <![endif]-->
</head>

<body>
    <section id="page">
        <header>
            <nav>
                <jsp:invoke fragment="header_area"/>
                <ul>
                    <li><a class="short-a" href="/guest/home">Home</a></li>
                    <li><a class="short-a" href="/guest/contests">Contests</a></li>
                    <li><a href="/guest/allTimeBest">AllTimeBest</a></li>
                    <li><a href="/guest/photographers">Photographers</a></li>
                    <li><a href="/guest/other" class="short-a">Other</a></li>
                </ul>
                <ul class="right-nav">
                    <li><a href="#" id="login-link">Login</a></li>
                    <li><a href="/guest/signUp">Sign Up</a></li>
                </ul>
            </nav>
            <div id="sec-nav">
                <div id="sec-nav-wrapper">
                    <ul>
                        <li><a href="#" id="nav-contest-name">Contest name</a></li>
                        <li>-</li>
                        <li><a href="#" class="under-link">All</a></li>
                        <li><a href="#" class="under-link">Top</a></li>
                        <li><a href="#" class="under-link">Newest</a></li>
                    </ul>
                </div>
            </div>
        </header>


        <section id="content">
            <!--login modal-->
            <div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true"
                 style="display:${display}; <c:if test="${display=='block'}"> visibility:visible; </c:if> ">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h1 class="text-center">Login</h1>
                        </div>
                        <div class="modal-body">
                            <form action="/submitSignInForm" method="post" class="form col-md-12 center-block">
                                <div class="form-group">
                                    <input type="text" class="form-control input-lg" placeholder="Email" name="email">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control input-lg" placeholder="Password" name="password">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
                                    <span class="pull-right"><a href="#">Need help?</a></span>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <div class="col-md-12">
                                <button id="form-close-button" class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                            </div>
                        </div>
                        <c:if test="${display=='block'}">
                            <p style="color:red; text-align: center;"> ${errorMessage}</p>
                        </c:if>
                    </div>
                </div>
            </div>
            <jsp:invoke fragment="content_area"/>
        </section>


        <footer>
            <jsp:invoke fragment="footer_area"/>
            <div id="footer-wrapper" class="wrapper">
                <div id="left-col" class="col">
                    <p class="footer-title">About us</p>
                    <br>
                    <p><a href="#">Contact</a></p>
                    <p><a href="#">Terms of service</a></p>
                    <p><a href="#">Privacy policy</a></p>
                </div>
                <div id="center-col" class="col">
                    <div id="logoG" class="social"><a href="#"></a></div>
                    <div id="logoF" class="social"><a href="#"></a></div>
                    <div id="logoY" class="social"><a href="#"></a></div>
                    <div id="logoI" class="social"><a href="#"></a></div>
                    <div id="logoT" class="social"><a href="#"></a></div>
                </div>
                <div id="right-col" class="col">
                    <p class="footer-title">Recent contests</p>
                    <br>
                    <p><a href="#">Contest 1</a></p>
                    <p><a href="#">Contest 2</a></p>
                    <p><a href="#">Contest 3</a></p>
                </div>

                <p id="rights">©2015 Aioanei Alexandru Andrei. All rights reserved.</p>
            </div>
        </footer>
    </section>
</body>
</html>