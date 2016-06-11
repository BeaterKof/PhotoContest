<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 1/13/16
  Time: 1:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:user-general-layout title="Home" last_name="${sessionScope.user.last_name}">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
        <div class="wrapper">
            <c:set var="flag" value="true"></c:set>

            <c:forEach items="${photographerList}" var="photographer">
                <c:choose>
                    <c:when test="${flag eq true}">
                        <c:set var="flag" value="false"></c:set>
                        <div class="photographer-wrapper">
                            <div class="photographer-name-left">
                                <a href="#">${photographer.first_name} ${photographer.last_name}</a>
                                <h6>Email address: ${photographer.email}</h6>
                                <h6>Description: ${photographer.description}</h6>
                            </div>
                            <c:if test="${not empty photographer.files}">
                                <div class="photographer-image-right">
                                    <img class="img-circle profile-photo" src="${pageContext.request.contextPath}/files/${photographer.files.get(0).path}">
                                </div>
                            </c:if>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <c:set var="flag" value="true"></c:set>
                        <div class="photographer-wrapper">
                            <c:if test="${not empty photographer.files}">
                                <div class="photographer-image-left" style="float: left;">
                                    <img class="img-circle profile-photo" src="${pageContext.request.contextPath}/files/${photographer.files.get(0).path}">
                                </div>
                            </c:if>
                            <div class="photographer-name-left" style="float: right">
                                <a href="/guest/userPage?userId=${photographer.user_id}">${photographer.first_name} ${photographer.last_name}</a>
                                <h6>Email address: ${photographer.email}</h6>
                                <h6>Description: ${photographer.description}</h6>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </jsp:attribute>

    <jsp:attribute name="footer_area">
    </jsp:attribute>
</t:user-general-layout>

<!-- Remove second navigation  -->
<script>
    document.getElementById("sec-nav").style.display = "none";
</script>