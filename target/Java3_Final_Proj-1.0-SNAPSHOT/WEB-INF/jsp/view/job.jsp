
<%@page import="com.kawski.Models.Job"%>
<%@page import="com.kawski.Models.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../jspf/scriptLib.jspf" />
        <title>${fn:escapeXml(Job.title)}</title>
    </head>  
    <body >
        <jsp:include page="../../jspf/navBar.jspf" />
        <header class="title-bar">
            <h1>${fn:escapeXml(Job.title)}</h1>
                <p class="jobDetailSubTitle">
                                <i class="material-icons">place</i>${fn:escapeXml(Job.location)}&nbsp;
                                <i class="material-icons">alarm</i>
                                <c:choose>
                                    <c:when test="${Job.fullTime}">
                                        Full Time
                                    </c:when>
                                    <c:otherwise>
                                        Part Time   
                                    </c:otherwise>
                                </c:choose>
                                <i class="material-icons">domain</i>${fn:escapeXml(Job.department)}&nbsp;
                                <i class="material-icons">school</i>${fn:escapeXml(Job.experience)}&nbsp;
                </p>
        </header>
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="container">
                        <div class="row">
                            <div class="jobRowDetail">
                                <p class="alignleft">Title</p><p class="alignright"> ${fn:escapeXml(Job.title)} </p>                               
                            </div>
                            <div class="jobRowDetail">
                                <p class="alignleft">Department</p><p class="alignright"> ${fn:escapeXml(Job.department)} </p>                               
                            </div>
                            <div class="jobRowDetail">
                                <p class="alignleft">Location</p><p class="alignright"> ${fn:escapeXml(Job.location)} </p>                               
                            </div> 
                            <div class="jobRowDetail">
                                <p class="alignleft">Wage</p>
                                <p class="alignright"> ${fn:escapeXml(Job.salary)}
                                <c:choose>
                                    <c:when test="${Job.wageCategory == 'Salaried'}">
                                        per year
                                    </c:when>
                                    <c:otherwise>
                                        per hour
                                    </c:otherwise>
                                </c:choose>
                                </p>
                                                              
                            </div>                            
                        </div>
                        <div class="row" id="jobDetails">
                            <h4>Job Details</h4>
                            <p>${fn:escapeXml(Job.jobDescription)}</p> 
                        </div>
                        <div class="row">
                               
                        </div>
                        
                    </div>
                </div>
                <div class="col">
                    <h3></h3>
                    <form method="POST" action="applications" enctype="multipart/form-data">
                        <legend>Apply for this position</legend>
                        <input type="hidden" name="action" value="create" />
                        <input type="hidden" name="jobTitle" value="${Job.title}"/>
                        <input type="hidden" name="jobId" value="${Job.id}"/>
                        <label for="firstName">First Name</label><br>
                        <c:if test="${Application.firstNameError != ''}"><label class="error">${Application.firstNameError}</label></c:if>
                        <input type="text" name="firstName" id="firstName"required/><br><br>
                        <label for="lastName">Last Name</label><br>
                        <c:if test="${Application.lastNameError != ''}"><label class="error">${Application.lastNameError}</label></c:if>
                        <input type="text" name="lastName" id="lastName" required/><br><br>
                        <label for="email">Email</label><br>
                        <c:if test="${Application.emailError != ''}"><label class="error">${Application.emailError}</label></c:if>
                        <input type="email" name="email" id="email" required/><br><br>
                        <label for="phone">Phone</label><br>
                        <c:if test="${Application.phoneError != ''}"><label class="error">${Application.phoneError}</label></c:if>
                        <input type="text" name="phone" id="phone" required/><br><br>
                        <label for="salary">Desired Salary</label><br>
                        <c:if test="${Application.salaryError != ''}"><label class="error">${Application.salaryError}</label></c:if>
                        <input type="number" name="salary" id="salary" required/><br><br>
                        <label for="earliestStartDate">Earliest Start Date</label><br>
                        <c:if test="${Application.startDateError != ''}"><label class="error">${Application.startDateError}</label></c:if>
                        <input type="date" name="earliestStartDate" id="earliestStartDate" required/><br><br>
                        <label for="file1">Upload Resume</label><br>
                        <input type="file" name="file1" id="file1"/><br><br>
                        <input type="submit" value="Submit"/>
                    </form>   
                </div>
            </div>
        </div>
    </body>
</html>

