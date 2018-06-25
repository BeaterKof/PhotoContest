<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 1/3/16
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:user-general-layout title="Home" last_name="${user.last_name}">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">

        <div id="content-wrapper" class="wrapper" style="margin-top: 10px;">
            <c:forEach items="${lastContest.fileList}" var="file">
                <div class="image-cell">
                    <div class="image-name image-toggle"><p class="name-parag">${file.name}</p></div>
                    <a class="fancybox" href="${pageContext.request.contextPath}/files/${file.path}">
                        <img src="${pageContext.request.contextPath}/files/${file.path}" class="image-itself" alt="${file.description}">
                    </a>
                    <div class="image-options image-toggle" id="${lastContest.contest_id}">
                        <img class="img_icon report_btn" id="${file.file_id}" src="/resources/images/red-alert.ico" title="Report">
                    </div>
                </div>
            </c:forEach>

            <div id="reportWindow" class="form-group">
                <textarea id="reportText" class="form-control" name="description" cols="50" rows="2" maxlength="100">Insert report reason</textarea>
                <button id="submitReport" type="button" class="btn btn-lg">Submit Report</button>
                <button id="closeReportWindow" type="button" class="btn btn-lg btn-danger">Close</button>
            </div>
        </div>

    </jsp:attribute>


    <jsp:attribute name="footer_area">
        <script type="text/javascript">
            $(document).ready(function(){
                $('.report_btn').click(function(){
                    $('#reportWindow').css('display','block');
                    $('#reportWindow').css('visibility','visible');
                    $('#reportText').focus();
                    var fileId =  $(this).attr('id');
                    var contestId =  $(this).parent().attr('id');
                    $.ajax({
                        type:'GET',
                        data: {fileId: fileId, contestId: contestId},
                        url:'userAjax/loadFileId'
                    });
                    return false;
                });

                $('#submitReport').click(function(){
                    var reportContent =  $('#reportText').val();
                    $.ajax({
                        type:'GET',
                        data: {reportContent: reportContent},
                        url:'userAjax/submitReport',
                        success: function(){
                                $('#reportWindow').fadeOut(500);
                        }
                    });
                    return false;
                });

                $('#closeReportWindow').click(function(){
                    $('#reportWindow').fadeOut(500);
                });
            });
        </script>
    </jsp:attribute>
</t:user-general-layout>