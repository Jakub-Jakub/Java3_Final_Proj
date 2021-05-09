
<%@page import="com.kawski.Models.Job"%>
<%@page import="com.kawski.Models.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../jspf/scriptLib.jspf" />
        <title>${fn:escapeXml(Application.jobTitle)}</title>
    </head>  
    <body >
        <jsp:include page="../../jspf/navBar.jspf" />
        <header class="title-bar">
            <h1>Application Details</h1>
        </header>
        <div class="container">
            <div class="row">
                            <div class="jobRowDetail">
                                <p class="alignleft">Title</p><p class="alignright"> ${fn:escapeXml(Application.jobTitle)} </p>                               
                            </div>
                            <div class="jobRowDetail">
                                <p class="alignleft">Applicant Name</p><p class="alignright">${fn:escapeXml(Application.firstName)}&nbsp;${fn:escapeXml(Application.lastName)}</p>                               
                            </div>
                            <div class="jobRowDetail">
                                <p class="alignleft">Email</p><p class="alignright"> ${fn:escapeXml(Application.email)} </p>                               
                            </div> 
                            <div class="jobRowDetail">
                                <p class="alignleft">Phone</p><p class="alignright"><c:out value="(${fn:substring(Application.phone, 0, 3)}) ${fn:substring(Application.phone, 3, 6)}-${fn:substring(Application.phone, 6, fn:length(Application.phone))}"/></p>                               
                            </div>
                            <div class="jobRowDetail">
                                <p class="alignleft">Desired Salary</p><p class="alignright"><fmt:formatNumber type="currency" value="${fn:escapeXml(Application.desiredSalary)}"/></p>                               
                            </div>
                            <div class="jobRowDetail">
                                <p class="alignleft">Earliest Start Date</p><p class="alignright"><fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${Application.startDate}"/></p>                               
                            </div>
                            <div class="jobRowDetail">
                                <p class="alignleft">Resume</p><p class="alignright"><a href="<c:url value="/applications"><c:param name="action" value="download"/><c:param name="appId" value="${Application.id}"/></c:url>">Download Resume</a></p>                               
                            </div>
                            <div class="jobRowDetail">
                            <form action="applications" method="get" >
                                <input type="hidden" name="action" value="deactivate" />
                                <input type="hidden" name="id" value="${Application.id}" />
                                <button type="submit" id="deactivateForm">Deactivate</button>
                            </form>
                            </div>
            </div>
        </div>
    </body>
