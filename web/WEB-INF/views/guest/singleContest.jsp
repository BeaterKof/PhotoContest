<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 2/4/16
  Time: 7:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:general-layout title="Contest ${contest.name}">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
        <div id="content-wrapper" class="wrapper" style="margin-top: 10px;">
            <c:forEach items="${contest.fileList}" var="file">
                <div class="image-cell">
                    <div class="image-name image-toggle"><p class="name-parag">${file.name}</p></div>
                    <img src="${pageContext.request.contextPath}/files/${file.path}" class="image-itself">
                    <div class="image-options image-toggle" id="${file.file_id}">
                        <img class="img_icon like_btn" src="/resources/images/white-heart.png" title="Like">
                        <span class="vote_number" style="color:red; float:right;">${fn:length(file.voterList)} </span>
                    </div>
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