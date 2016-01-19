<!DOCTYPE html>
<%@tag description="General Template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="head" fragment="true" %>
<%@attribute name="content_area" fragment="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>PhotoContest - ${title}</title>
        <jsp:invoke fragment="head"/>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <link href="<spring:url value='/resources/css/admin_styles.css'/>" rel="stylesheet" />
        <link href="<spring:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<spring:url value='/resources/js/jquery-2.1.4.js'/>"></script>
        <script type="text/javascript" src="<spring:url value='/resources/js/admin-script.js'/>"></script>
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
        <jsp:invoke fragment="content_area"/>
    </body>
</html>