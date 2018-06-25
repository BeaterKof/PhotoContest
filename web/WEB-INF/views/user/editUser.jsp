<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 1/9/16
  Time: 1:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<t:user-general-layout title="Home" last_name="${sessionScope.user.last_name}">
    <jsp:attribute name="head">
        <script type="text/javascript" src="<spring:url value='/resources/js/account-script.js'/>"></script>
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
        <div id="content-wrapper-user-edit" class="wrapper">
            <div class="page-header" style="text-align:center; font-size:30px">
                Account
            </div>

            <!-- For JS -->
            <div class="alert alert-danger" role="alert" id="formErrorMessageContainer" style="visibility:hidden">
                <span class="sr-only">Error:</span>
                <div id="formErrorMessage"></div>
            </div>

            <c:if test="${not empty errorMessage}">
                <div class="form-group">
                    <div class="alert alert-danger">
                        <strong>${errorMessage}</strong>
                    </div>
                </div>
            </c:if>

            <div id="account-edit-buttons">
                <button onclick="displayPhotos()" type="button" class="btn btn-lg btn-success">My Photos</button>
                <button onclick="displayForm()" id="changeAccountButton" type="button" class="btn btn-lg btn-warning">Change account</button>
                <button onclick="displayPassword()" id="changePasswordButton" type="button" class="btn btn-lg btn-primary">Change Password</button>
                <button onclick="displayDeletionPrompt()" id="deleteAccountButton"  type="button" class="btn btn-lg btn-danger">Delete Account</button>
            </div>



            <div id="deletionPrompt">
                <div class="alert alert-danger">
                    <p>Are you shure you want to delete the account?</p>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-success" onclick="closeDeletionPrompt()">No</button>
                        <a href="/deleteUser"><button type="button" class="btn btn-warning" >Yes</button></a>
                    </div>
                </div>
            </div>

            <form id="accountForm" action="/user/modifyUserAccount" method="post" class="form-horizontal" role="form"
                  onsubmit="return validateForm()" style="display:none;">
                <div class="form-group">
                    <label class="col-xs-3 control-label">Name</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="newFirstName" placeholder="First name"
                               value="${user.first_name}"/>
                    </div>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="newLastName" placeholder="Last name"
                                value="${user.last_name}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Email</label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="newEmail" value="${user.email}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Webpage</label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="newWebsite" maxlength="50" value="${user.website}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">About yourself</label>
                    <div class="col-xs-5">
                        <label>
                            <textarea class="form-control" name="newDescription" rows="3" maxlength="200">${user.description}</textarea>
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-9 col-xs-offset-3">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </form>


            <div id="accountPassword" style="display:none;" onsubmit="return validatePassword()">
                <form id="passwordForm" action="/user/modifyUserPassword" method="post" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Old Password</label>
                        <div class="col-xs-5">
                            <input type="password" class="form-control" name="oldPass" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">New Password</label>
                        <div class="col-xs-5">
                            <input type="password" class="form-control" name="newPass" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-9 col-xs-offset-3">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>

                </form>
            </div>




            <div id="accountPhotos" style="display:none;">

                <button onclick="displayUploadForm()" type="button" class="btn btn-lg">Show upload file form</button>

                <form id="fileInputForm" class="form-horizontal" action="/user/uploadFile" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-xs-3 control-label">File name</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" name="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Description</label>
                        <div class="col-xs-4">
                            <textarea class="form-control" name="description" rows="1" maxlength="100"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-9 col-xs-offset-3">
                            <label class="col-xs-3 control-label">File ( < 5MB )</label>
                            <input id="fileInput" name="image" type="file" alt="your image" onchange="readURL(this);" />
                            <img id="thumbnail-image" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-9 col-xs-offset-3">
                            <input type="submit" class="btn btn-primary" value="Upload file!">
                        </div>
                    </div>

                </form>

                <div id="content-wrapper" class="wrapper" style="margin-top: 10px;">
                    <c:forEach items="${user.files}" var="file">
                        <div class="image-cell">
                            <div class="image-name image-toggle"><p class="name-parag">${file.name}</p></div>
                            <a class="fancybox" rel="group" href="${pageContext.request.contextPath}/files/${file.path}">
                                <img src="${pageContext.request.contextPath}/files/${file.path}" class="image-itself" alt="${file.description}">
                            </a>
                            <div class="image-options image-toggle" id="${file.file_id}">
                                <img class="img_icon toContest" src="/resources/images/green-arrow.ico" title="Enter contest">
                                <img class="img_icon removeBtn" src="/resources/images/delete.png" title="Delete">
                            </div>
                        </div>
                    </c:forEach>

                    <div id="chooseContest">
                        <p style="padding-top: 10px">Available contests</p>
                        <button type="button" class="btn btn-danger buton_contest" id="0">Remove from contest</button>
                        <c:forEach items="${contestList}" var="contest">
                            <button type="button" class="btn buton_contest" id="${contest.contest_id}">${contest.name}</button>
                        </c:forEach>
                        <button type="button" class="btn btn-danger" id="closeContestList">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>


    <jsp:attribute name="footer_area">
        <script type="text/javascript" src="<spring:url value='/resources/js/account-script.js'/>"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('.removeBtn').click(function(){
                    var fileId = $(this).parent().attr('id');
                    $.ajax({
                        type:'GET',
                        data: {fileId: fileId},
                        url:'userAjax/deletePhoto',
                        success: function(){
                            $('#' + fileId).closest('.image-cell').fadeOut(700);
                        }
                    });
                    return false;
                });

                $('.buton_contest').click(function(){
                    var contestId = $(this).attr('id');
                    $.ajax({
                        type:'GET',
                        data: {contestId: contestId},
                        url:'userAjax/enterContest',
                        success: function(){
                            $('#chooseContest').fadeOut(700);
                        }
                    });
                    return false;
                });

                $('.toContest').click(function(){
                    $('#chooseContest').css('display','block');
                    $('#chooseContest').css('visibility','visible');
                    var fileId =  $(this).parent().attr('id');

                    $.ajax({
                        type:'GET',
                        data: {fileId: fileId},
                        url:'userAjax/loadFileId'
                    });
                    return false;
                });

                $('#closeContestList').click(function(){
                    $('#chooseContest').fadeOut(500);
                });
            });
        </script>
    </jsp:attribute>
</t:user-general-layout>

<!-- Remove second navigation  -->
<script>
    document.getElementById("sec-nav").style.display = "none";
</script>