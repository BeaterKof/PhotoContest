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
                    <img src="${pageContext.request.contextPath}/files/${file.path}" class="image-itself">
                    <div class="image-options" id="${file.file_id}">
                        <span class=".picture_name" style="color:white;font-size: 20px;">${file.name}</span>
                        <img class="img_icon report_btn" src="/resources/images/red-alert.ico" title="Report">
                    </div>
                </div>
            </c:forEach>

            <div id="reportWindow" class="form-group">
                <textarea id="reportText" class="form-control" name="description" cols="50" rows="2" maxlength="100">Insert report reason</textarea>
                <button id="submitReport" type="button" class="btn btn-lg">Submit Report</button>
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
                    var fileId =  $(this).parent().attr('id');
                    $.ajax({
                        type:'GET',
                        data: {fileId: fileId},
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
                            $('#reportWindow').focusout(function(){
                                $('#reportWindow').fadeOut(500);
                            });
                        }
                    });
                    return false;
                });

//                $('#reportWindow').focusout(function(){
//                    $('#reportWindow').fadeOut(500);
//                });
            });
        </script>
    </jsp:attribute>
</t:user-general-layout>