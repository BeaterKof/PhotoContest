<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 12/14/15
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:general-layout title="Home">
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
                        <img class="img_icon like_btn" src="/resources/images/white-heart.png" title="Like">
                        <span class="vote_number" style="color:red; float:right;">${fn:length(file.voterList)}</span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:attribute>


    <jsp:attribute name="footer_area">
        <script type="text/javascript">
            var clientIp;
            $.get("http://ipinfo.io", function(response) {
                clientIp = response.ip;
            }, "jsonp");
            $(document).ready(function(){
                $('.like_btn').click(function(){
                    var fileId = $(this).parent().attr('id');
                    $.ajax({
                        type:'GET',
                        data: {fileId: fileId, clientIp: clientIp},
                        url:'userAjax/likePhoto',
                        success: function(){
                            $('#' + fileId).find('img').attr("src","/resources/images/red-heart.png");
                            $('#' + fileId).find('img').css("pointer-events","none");
                        }
                    });
                    return false;
                });
            });
        </script>
        <style type="text/css">
            #${secondMenu}{
                text-decoration: underline;
            }
        </style>
    </jsp:attribute>
</t:general-layout>