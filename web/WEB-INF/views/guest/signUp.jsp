<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 12/28/15
  Time: 10:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<spring:url value='/resources/js/form-script.js'/>"></script>

<t:general-layout title="Sign Up">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>

    <jsp:attribute name="content_area">
        <div id="content-wrapper-signup" class="wrapper">
            <form id="contactForm" action="/submitSignUpForm" class="form-horizontal" onsubmit="return validateForm()" role="form" method="post">
                <div class="form-group">
                    <div>
                        <h1 style="text-align:center;">Sign-Up</h1>
                    </div>
                    <label class="col-xs-3 control-label">Full name</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="first_name" placeholder="First name" />
                    </div>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="last_name" placeholder="Last name" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Email address*</label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="email" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Password*</label>
                    <div class="col-xs-5">
                        <input type="password" class="form-control" name="password" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Personal webpage</label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="website" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">About yourself <br> (max 200 characters)</label>
                    <div class="col-xs-5">
                        <textarea class="form-control" name="description" rows="3" maxlength="200"></textarea>
                    </div>
                    </textarea>
                </div>

                <div class="alert alert-danger" role="alert" id="formErrorMessageContainer" style="visibility:hidden">
                    <span class="sr-only">Error:</span>
                    <div id="formErrorMessage"></div>
                </div>

                <c:if test="${not empty errorString}">
                        <p style="text-align: center"> Server error: ${errorString} </p>
                </c:if>


                <div class="form-group">
                    <div class="col-xs-9 col-xs-offset-3">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </form>
        </div>

    </jsp:attribute>


    <jsp:attribute name="footer_area">
            <script type="text/javascript" src="<spring:url value='/resources/js/form-script.js'/>"></script>
    </jsp:attribute>

</t:general-layout>

<!-- Remove second navigation and focus -->
<script>
    document.getElementById("sec-nav").style.display = "none";
    document.forms["contactForm"]["firstName"].focus();
</script>
