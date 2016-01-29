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
            <button type="button" class="btn btn-lg btn-success rollback_btn">Rollback</button>
            <button type="button" class="btn btn-lg btn-info menu_button">Admin Account Management</button>
            <a href="/admin/logout"><button type="button" class="btn btn-lg btn-default menu_button">Logout</button></a>
        </div>


        <script type="text/javascript">
            $(document).ready(function(){
                $('.rollback_btn').click(function(){
                    var userId = $(this).parent().attr('id');
                    $.ajax({
                        type:'GET',
                        data: {},
                        url:'/admin/',
                        success: function(){
                            if(action.isEqual('delete')){
                                $('#' + userId).closest('tr').fadeOut(700);
                            }
                        }
                    });
                    return false;
                });
            });
        </script>
    </jsp:attribute>
</t:admin-layout>

