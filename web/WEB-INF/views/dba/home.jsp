<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 1/23/16
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 1/18/16
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:admin-layout title="Home">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="content_area">
        <div id="nav_buttons">
            <button type="button" class="btn btn-lg btn-success backup_btn">Backup</button>
            <button type="button" class="btn btn-lg btn-info recovery_button">Recovery</button>
            <a href="/admin/logout"><button type="button" class="btn btn-lg btn-default menu_button">Logout</button></a>
        </div>


        <script type="text/javascript">
            $(document).ready(function(){
                $('.backup_btn').click(function(){
                    $.ajax({
                        type:'GET',
                        data: {},
                        url:'adminAjax/backup',
                        success: function(){
                            alert("Backup Successful");
                        }
                    });
                    return false;
                });
            });
            $(document).ready(function(){
                $('.recovery_btn').click(function(){
                    $.ajax({
                        type:'GET',
                        data: {},
                        url:'adminAjax/recovery',
                        success: function(){
                            alert("Recovery Successful");
                        }
                    });
                    return false;
                });
            });
        </script>
    </jsp:attribute>
</t:admin-layout>

