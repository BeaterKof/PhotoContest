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

<script type="text/javascript" src="<spring:url value='/resources/js/form-script.js'/>"></script>

<t:general-layout title="Home">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>

    <jsp:attribute name="content_area">
        <div id="content-wrapper-signup" class="wrapper" >
            <form id="contactForm" class="form-horizontal"  onsubmit="return validateForm()">
                <div class="form-group">
                    <div class="page-header">
                        <h1 style="text-align:center;">Sign-Up</h1>
                    </div>
                    <label class="col-xs-3 control-label">Full name</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="firstName" placeholder="First name" />
                    </div>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="lastName" placeholder="Last name" />
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


                <div class="alert alert-danger" role="alert" id="formErrorMessageContainer" style="visibility:hidden">
                    <span class="sr-only">Error:</span>
                    <div id="formErrorMessage"></div>
                </div>


                <div class="form-group">
                    <div class="col-xs-9 col-xs-offset-3">
                        <button type="submit" class="btn btn-primary" >Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </jsp:attribute>


    <jsp:attribute name="footer_area">
            <script type="text/javascript" src="<spring:url value='/resources/js/form-script.js'/>"></script>
    </jsp:attribute>

</t:general-layout>