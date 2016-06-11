<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 2/4/16
  Time: 7:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:user-general-layout title="Contest ${lastContest.name}" last_name="${sessionScope.user.last_name}">
    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="header_area">
    </jsp:attribute>


    <jsp:attribute name="content_area">
        <div id="content-wrapper" class="wrapper" style="margin-top: 10px;">
            <c:if test="${not empty winner}"><div class="alert alert-success">The winner is: ${winner.first_name} ${winner.last_name}</div></c:if>
            <c:forEach items="${lastContest.fileList}" var="file">
                <div class="image-cell">
                    <div class="image-name image-toggle"><p class="name-parag">${file.name}</p></div>
                    <img src="${pageContext.request.contextPath}/files/${file.path}" class="image-itself" alt="${file.description}">
                    <div class="image-options image-toggle" id="${file.file_id}">
                        <img class="img_icon report_btn" id="${file.file_id}" src="/resources/images/red-alert.ico" title="Report">
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
                                url:'/guest/userAjax/likePhoto',
                                success: function(){
                                    $('#' + fileId).find('img').attr("src","/resources/images/red-heart.png");
                                    $('#' + fileId).find('img').css("pointer-events","none");
                                }
                            });
                            return false;
                        });
                    });
                </script>
    </jsp:attribute>
</t:user-general-layout>

<!-- Remove second navigation  -->
<script>
    var elems = document.getElementsByClassName("sec-nav-opt");
    for(var i = 0; i < elems.length; i++) {
        elems[i].style.display = "none";
    }
</script>