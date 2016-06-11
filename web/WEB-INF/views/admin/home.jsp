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
            <button onclick="showUserMng()" type="button" class="btn btn-lg btn-success menu_button">User Account Management</button>
            <button onclick="showAdminMng()" type="button" class="btn btn-lg btn-info menu_button">Admin Account Management</button>
            <button onclick="showContestMng()" type="button" class="btn btn-lg btn-warning menu_button">Contest Management</button>
            <button onclick="showReportsMng()" type="button" class="btn btn-lg btn-default menu_button">Reports Management</button>
            <a href="/admin/logout"><button type="button" class="btn btn-lg btn-default menu_button">Logout</button></a>
        </div>

        <div id="admin_content">
            <h3>Deactivated accounts have white background.</h3>
            <div id="user_mng">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email Address</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userList}" var="user" varStatus="counter">
                            <tr <c:if test="${user.status == 0}">style="background-color: white" </c:if> >
                                <th>${counter.index}</th>
                                <th>${user.last_name}</th>
                                <th>${user.first_name}</th>
                                <th>${user.email}</th>
                                <th id="${user.user_id}"><button type="button" name="deactivate" class="btn btn-warning user_btn">Activate/Deactivate</button></th>
                                <th id="${user.user_id}"><button type="button" name="delete" class="btn btn-danger user_btn">Delete</button></th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>




            <div id="admin_mng">
                <a href="/admin/getAdminForm" class="add_button"><button type="button" class="btn btn-primary">Add new admin account</button></a>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Email Address</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${adminList}" var="adminItem" varStatus="counter">
                            <tr>
                                <th>${counter.index}</th>
                                <th>${adminItem.name}</th>
                                <th>${adminItem.email}</th>
                                <th id="${adminItem.admin_id}"><button type="button" class="btn btn-danger admin_btn">Delete</button></th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>





            <div id="contest_mng">
                <a href="/admin/getContestForm" class="add_button"><button type="button" class="btn btn-primary">Create new contest</button></a>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Id</th>
                        <th>Prize</th>
                        <th>Starting date</th>
                        <th>Ending date</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${contestList}" var="contest" varStatus="counter">
                            <tr>
                                <th>${counter.index}</th>
                                <th>${contest.name}</th>
                                <th>${contest.contest_id}</th>
                                <th>${contest.prize}</th>
                                <th>${contest.start_date}</th>
                                <th>${contest.finish_date}</th>
                                <th id="${contest.contest_id}"><button type="button" class="btn btn-danger contest_btn">Delete</button></th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>





            <div id="reports_mng">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Message</th>
                        <th>File id</th>
                        <th>Contest id</th>
                        <th>Reporter email</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${reportList}" var="report" varStatus="counter">
                            <tr id="${report.report_id}" name="${report.file.file_id}">
                                <th>${counter.index}</th>
                                <th>${report.message}</th>
                                <th>${report.file.file_id}</th>
                                <th>${report.contest.contest_id}</th>
                                <th>${report.reporter_email}</th>
                                <th><button type="button" name="removeFromContest" class="btn btn-danger report_btn">Remove from contest</button></th>
                                <th><button type="button" name="delete" class="btn btn-danger report_btn">Delete report</button></th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function(){
                <!-- Activate/deactivate user -->
                $('.user_btn').click(function(){
                    var userId = $(this).parent().attr('id');
                    var action = $(this).attr('name');
                    $.ajax({
                        type:'GET',
                        data: {userId: userId, action: action},
                        url:'/admin/adminUserManager',
                        success: function(){
                            if(action === 'delete'){
                                $('#' + userId).closest('tr').fadeOut(700);
                            }
                            if(action === 'deactivate'){
                                if($('#' + userId).closest('tr').css("background-color") == "rgb(255, 255, 255)"){
                                    $('#' + userId).closest('tr').css("background-color","#F9F9F9");
                                    alert("deactivate true!");
                                } else {
                                    $('#' + userId).closest('tr').css("background-color","white");
                                    alert("deactivate false!");
                                }
                            }
                        }
                    });
                    return false;
                });

                <!-- Delete admin -->
                $('.admin_btn').click(function(){
                    var adminId = $(this).parent().attr('id');
                    $.ajax({
                        type:'GET',
                        data: {adminId: adminId},
                        url:'/admin/adminAdminManager',
                        success: function(){
                            $('#' + adminId).closest('tr').fadeOut(700);
                        }
                    });
                    return false;
                });

                <!-- Delete contest -->
                $('.contest_btn').click(function(){
                    var contestId = $(this).parent().attr('id');
                    $.ajax({
                        type:'GET',
                        data: {contestId: contestId},
                        url:'/admin/adminContestManager',
                        success: function(){
                            $('#' + contestId).closest('tr').fadeOut(700);
                        }
                    });
                    return false;
                });

                <!-- Delete report -->
                $('.report_btn').click(function(){
                    var reportId = $(this).closest('tr').attr('id');
                    var fileId = $(this).closest('tr').attr('name');
                    var action = $(this).attr('name');
                    $.ajax({
                        type:'GET',
                        data: {reportId: reportId, action: action},
                        url:'/admin/adminReportsManager',
                        success: function(){
                                $('#' + reportId).closest('tr').fadeOut(700);
                        }
                    });
                    return false;
                });
            });
        </script>
    </jsp:attribute>
</t:admin-layout>

