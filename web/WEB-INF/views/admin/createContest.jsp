<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 1/18/16
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:admin-layout title="Home">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="content_area">
        <div class="container my_container">
            <h2 style="text-align:center">New Contest Info</h2>
            <form role="form" action="/admin/createNewContest" method="post">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" name="name" class="form-control" id="name" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label for="prize">Prize:</label>
                    <input type="number" name="prize" class="form-control" id="prize">
                </div>
                <div class="form-group">
                    <label>Start date:</label>
                    <input type="date" name="start_date" class="form-control" id="start_date">
                </div>
                <div class="form-group">
                    <label>Finish date:</label>
                    <input type="text" name="finish_date" class="form-control" id="finish_date">
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <textarea class="form-control" name="description" maxlength="100" rows="2" cols="50"></textarea>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="form-group alert alert-danger">
                        <h4>${errorMessage}</h4>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="/admin"><button type="button" class="btn btn-warning">Go Back</button></a>
            </form>
        </div>

        <script type="text/javascript">
            $(document).ready(function(){
                $("#name").focus();
            });
        </script>
    </jsp:attribute>
</t:admin-layout>

