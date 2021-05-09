
<%@page import="com.kawski.Models.Job"%>
<%@page import="com.kawski.Models.Pagination"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../jspf/scriptLib.jspf" />
        <title>Jobs</title>
    </head>  
    <body >
        <jsp:include page="../../jspf/navBar.jspf" />
        <div class="container">
            <div class="row">
                <div class="col">
            <div class="container">
                <div class="row">
                    <h1>Job List</h1>
                </div>
                <div class="row">
            <c:forEach var="Job" items="${Jobs}" begin="${Pagination.firstIndexOnPage}" end="${Pagination.lastIndexOnPage}">
                <a href="<c:url value="/jobs"><c:param name="id" value="${Job.id}"/></c:url>"><h3>${fn:escapeXml(Job.title)}</h3></a>
                <p class="jobSubTitle"><i class="material-icons">place</i>${fn:escapeXml(Job.location)}&nbsp;${fn:escapeXml(Job.department)}</p>
            </c:forEach>
                </div>
                <div class="row">
                    <nav>
                        <ul class="pagination">
                    <c:forEach var="i" begin="1" end="${Pagination.maxPages}">
                        <li class="page-item"><a <c:choose><c:when test="${Pagination.currentPage == i}">class="active page-link"</c:when><c:otherwise>class="page-link"</c:otherwise></c:choose> 
                            href="<c:url value="/jobs"><c:param name="page" value="${i}" /></c:url>">
                            ${i}
                            </a></li>              
                    </c:forEach>
                            </ul>
                    </nav>
                </div>
            </div>
                </div>
                <div class="col-md-6 float">
                    <img src="img/careers-jazz-img.png"/>
                </div>
                </div>
        </div>
    </body>
</html>
