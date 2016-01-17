<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 1/13/16
  Time: 1:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:user-general-layout title="Home" last_name="${sessionScope.user.last_name}">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
        <div class="wrapper">

        </div>
    </jsp:attribute>


    <jsp:attribute name="footer_area">
    </jsp:attribute>
</t:user-general-layout>