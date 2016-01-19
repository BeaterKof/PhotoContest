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

<t:admin-layout title="Home">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="content_area">
        <div id="nav_buttons">
            <button onclick="showUserMng()" type="button" class="btn btn-lg btn-success menu_button">User Account Management</button>
            <button onclick="showAdminMng()" type="button" class="btn btn-lg btn-info menu_button">Admin Account Management</button>
            <button onclick="showContestMng()" type="button" class="btn btn-lg btn-warning menu_button">Contest Management</button>
            <button onclick="showReportsMng()" type="button" class="btn btn-lg btn-default menu_button">Reports Management</button>
        </div>

        <div id="admin_content">
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
                    <tr>
                        <th>1</th>
                        <th>Ionel</th>
                        <th>Amarie</th>
                        <th>ionel.amarie@gmail.com</th>
                        <th><button type="button" class="btn btn-warning">Deactivate</button></th>
                        <th><button type="button" class="btn btn-danger">Delete</button></th>
                    </tr>
                    </tbody>
                </table>
            </div>




            <div id="admin_mng">
                <a href="/admin/getAdminForm" class="add_button"><button type="button" class="btn btn-primary">Add new admin account</button></a>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email Address</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>1</th>
                        <th>Ionel</th>
                        <th>Amarie</th>
                        <th>ionel.amarie@gmail.com</th>
                        <th><button type="button" class="btn btn-danger">Delete</button></th>
                    </tr>
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
                        <th>Prize</th>
                        <th>Starting date</th>
                        <th>Ending date</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>1</th>
                        <th>Nume concurs</th>
                        <th>2000$</th>
                        <th>20 Noe 2012</th>
                        <th>30 Mar 2013</th>
                        <th><button type="button" class="btn btn-danger">Delete</button></th>
                    </tr>
                    </tbody>
                </table>
            </div>





            <div id="reports_mng">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>File name</th>
                        <th>Contest</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>1</th>
                        <th>Nume fisier</th>
                        <th>Nume concurs</th>
                        <th><button type="button" class="btn btn-danger">Remove from contest</button></th>
                        <th><button type="button" class="btn btn-danger">Delete file</button></th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:attribute>
</t:admin-layout>
