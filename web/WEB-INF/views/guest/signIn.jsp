<%--
  Created by IntelliJ IDEA.
  User: Aioanei Andrei
  Date: 2/20/16
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:general-layout title="SignIn">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
        <div class="wrapper">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="text-center">Login</h1>
                    </div>
                    <div id="modal-fix" class="modal-body">
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
                    <c:if test="${not empty errorMessage}">
                        <p class="alert alert-danger"> ${errorMessage}</p>
                    </c:if>
                </div>
            </div>
        </div>
    </jsp:attribute>


    <jsp:attribute name="footer_area">
    </jsp:attribute>
</t:general-layout>

<!-- Remove second navigation  -->
<script>
    document.getElementById("sec-nav").style.display = "none";
</script>


