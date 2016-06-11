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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:general-layout title="All Time Best">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
                <div id="content-wrapper" class="wrapper" style="margin-top: 10px;">
                    <c:forEach items="${allTimeBestList}" var="file">
                        <div class="image-cell">
                            <div class="image-name image-toggle"><p class="name-parag">${file.name}</p></div>
                            <img src="${pageContext.request.contextPath}/files/${file.path}" class="image-itself" alt="${file.description}">
                            <div class="image-options image-toggle" id="${file.file_id}">
                                <span id="photographer_link" class="vote_number" style="float:left;"> by <a href="/guest/singlePhotographer?photographerId=${file.user.user_id}">${file.user.first_name}</a></span>
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