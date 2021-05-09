
<%@page import="com.kawski.Models.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../jspf/scriptLib.jspf" />
        <title>Applications</title>
    </head>  
    <body >
        <jsp:include page="../../jspf/navBar.jspf" />
        <div class="container applicationListBody">
                <div class="row">
                    <h1>Applications List</h1>
                </div>
            <c:if test="${fn:length(Applications)==0}"><h2>There are no applications...</h2></c:if>    
            <c:forEach var="Application" items="${Applications}">
                <c:if test="${Application.active}">
                <div class="row applicationRow"><div class="col-sm">
                <a href="<c:url value="/applications"><c:param name="action" value="view"/><c:param name="id" value="${Application.id}"/></c:url>"><h3>${fn:escapeXml(Application.jobTitle)}</h3></a>
                <p>Applicant Name:&nbsp;${fn:escapeXml(Application.firstName)}&nbsp;${fn:escapeXml(Application.lastName)}</p>
                <p>Applicant Email:&nbsp;${fn:escapeXml(Application.email)}</p>
                </div></div>
                </c:if>
            </c:forEach>
                
        </div>
    </body>
</html>

