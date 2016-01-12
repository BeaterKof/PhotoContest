<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 12/29/15
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:general-layout title="Home">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
        <div class="wrapper"></div>
    </jsp:attribute>


    <jsp:attribute name="footer_area">
    </jsp:attribute>
</t:general-layout>

<!-- Remove second navigation  -->
<script>
    document.getElementById("sec-nav").style.display = "none";
</script>