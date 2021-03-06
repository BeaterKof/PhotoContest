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
<jsp:useBean id="now" class="java.util.Date" />

<t:general-layout title="Contests">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>

    <jsp:attribute name="content_area">
        <div class="wrapper">
            <c:forEach var="contest" items="${contestList}">
                <div class="contest-wrapper">
                    <div><a href="/guest/singleContest?contestId=${contest.contest_id}"><c:out value="${contest.name}"/></a></div>
                    <c:choose>
                        <c:when test="${contest.finish_date.date < now.date}">
                            <p style="color: #c1e2b3; text-align:right;">Ongoing until <span color="blue">${contest.finish_date}</span></p>
                        </c:when>
                        <c:otherwise>
                            <p style="color: #d9534f; text-align:right;">Ended</p>
                        </c:otherwise>
                    </c:choose>
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