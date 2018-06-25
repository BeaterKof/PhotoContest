<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 1/18/16
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:admin-layout title="Home">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="content_area">
        <div class="container my_container">
            <h2 style="text-align:center">Admin Login</h2>
            <form role="form" action="/guest/adminLogin" method="post">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
                </div>
                <div class="form-group">
                    <h4 style="color:red">${errorMessage}</h4>
                </div>
                <button type="submit" class="btn btn-primary">Log In</button>
            </form>
        </div>

        <script type="text/javascript">
            $(document).ready(function(){
                $("#name").focus();
            });
        </script>
    </jsp:attribute>
</t:admin-layout>