<%--
  Created by IntelliJ IDEA.
  User: Aioanei Andrei
  Date: 5/28/16
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:general-layout title="${photographer.first_name}">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
         <div class="user_details_wrapper">
            <h1>User: ${photographer.first_name} ${photographer.last_name}</h1>
            <h2>Website: <a href="${photographer.website}">${photographer.website}</a></h2>
         </div>

         <div id="content-wrapper" class="wrapper" style="margin-top: 10px;">
            <c:forEach items="${photographer.files}" var="file">
                <div class="image-cell">
                    <div class="image-name image-toggle"><p class="name-parag">${file.name}</p></div>
                    <a class="fancybox" rel="group" href="${pageContext.request.contextPath}/files/${file.path}">
                        <img src="${pageContext.request.contextPath}/files/${file.path}" class="image-itself" alt="${file.description}">
                    </a>
                </div>
            </c:forEach>
        </div>
    </jsp:attribute>


    <jsp:attribute name="footer_area">
    </jsp:attribute>
</t:general-layout>

<!-- Remove second navigation  -->
<script>
    document.getElementById("sec-nav").style.display = "none";
</script>