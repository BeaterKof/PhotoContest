<!DOCTYPE html>
<%@tag description="General Template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="last_name"%>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header_area" fragment="true" %>
<%@attribute name="content_area" fragment="true" %>
<%@attribute name="footer_area" fragment="true" %>
<%@attribute name="content_area_login" fragment="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>PhotoContest - ${title}</title>
    <jsp:invoke fragment="head"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link href="<spring:url value='/resources/css/styles.css'/>" rel="stylesheet" />
    <link href="<spring:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet" />
    <script type="text/javascript" src="<spring:url value='/resources/js/jquery-2.1.4.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/resources/js/main-script.js'/>"></script>
    <!-- Fancybox -->
    <script type="text/javascript" src="<spring:url value='/resources/fancybox/jquery.fancybox.pack.js'/>"></script>
    <link href="<spring:url value='/resources/fancybox/jquery.fancybox.css'/>" rel="stylesheet" type="text/css" media="screen" />

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
        <jsp:invoke fragment="header_area"/>
        <header>
            <nav>
                <ul>
                    <li><a class="short-a" href="/user/home">Home</a></li>
                    <li><a class="short-a" href="/user/contests">Contests</a></li>
                    <li><a href="/user/allTimeBest">AllTimeBest</a></li>
                    <li><a href="/user/photographers">Photographers</a></li>
                    <li><a href="/user/other" class="short-a">Other</a></li>
                </ul>
                <ul class="right-nav">
                    <li><a id="account-link" href="/user/userAccount">${last_name}</a></li>
                    <li><a id="signout-link" href="/user/userSignOut">SignOut</a></li>
                </ul>
            </nav>
            <div id="sec-nav">
                <div id="sec-nav-wrapper">
                    <ul>
                        <li><a href="#" id="nav-contest-name">${lastContest.name}</a></li>
                    </ul>
                </div>
            </div>
        </header>

        <section id="content">
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
                    <c:forEach var="contest" items="${topThree}" varStatus="status">
                        <p><a href="/guest/singleContest?contestId=${contest.contest_id}">${contest.name}</a></p>
                    </c:forEach>
                </div>

                <p id="rights">©2015 Aioanei Alexandru Andrei. All rights reserved.</p>
            </div>
        </footer>
    </section>
</body>
</html>