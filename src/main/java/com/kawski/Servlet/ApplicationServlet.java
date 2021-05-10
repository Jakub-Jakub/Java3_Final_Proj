/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kawski.Servlet;

import com.kawski.Models.Application;
import com.kawski.Models.Attachment;
import com.kawski.Models.Job;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author jkaws
 */
@WebServlet(name = "ApplicationServlet", urlPatterns = {"/applications"})
@MultipartConfig(
        fileSizeThreshold = 5_242_880, //5MB
        maxFileSize = 20_971_520L //20MB
)
public class ApplicationServlet extends HttpServlet {
    private static final SortedSet<Application> applications = new TreeSet(); 
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            System.out.println("NULL ACTION PARAMETER");
            action = "list";
        }
        switch (action) {
            case "view":
                viewApplication(request, response);
                break;
            case "download":
                downloadAttachment(request, response);
                break;
            case "deactivate":
                int id = Integer.parseInt(request.getParameter("id"));
                for(Application app : applications){
                    if(app.getId()==id){
                        app.setActive(false);
                        break;
                    }
                }
            case "list":
            default:
                listApplications(request, response);
                break;
        }       
        
    }
    private void viewApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Application appToView = null;
        for(Application app : applications){
            if(app.getId()==id){
                appToView = app;
                break;
            }
        }
        System.out.println(appToView);
        if(appToView == null){
            response.sendRedirect("applications");
            return;
        }
        request.setAttribute("Application", appToView);
        request.getRequestDispatcher("/WEB-INF/jsp/view/application.jsp").forward(request, response);
    }
    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appId = Integer.parseInt(request.getParameter("appId"));
        Application appToDownloadFrom = null;
        for(Application app : applications){
            if(app.getId()==appId){
                appToDownloadFrom = app;
                break;
            }
        }
        if(appToDownloadFrom == null){
            response.sendRedirect("applications");
        }
        Attachment attachment = appToDownloadFrom.getResumeUpload();
        if (attachment == null) {
            response.sendRedirect("applications?action=view&id=" + appId);
            return;
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        try (ServletOutputStream stream = response.getOutputStream()) {
            stream.write(attachment.getContents());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        String action = request.getParameter("action");
        if (action == null) {
            System.out.println("NULL ACTION PARAMETER");
            action = "list";
        }
        switch (action) {
            case "create":
                createApplication(request, response);
                break;
            case "list":
            default:
                listApplications(request, response);
                break;
        }
        
        //request.getRequestDispatcher("/WEB-INF/jsp/view/jobList.jsp").forward(request, response);
    }
    
    private void listApplications(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        request.setAttribute("Applications", applications);
        request.getRequestDispatcher("/WEB-INF/jsp/view/applicationList.jsp").forward(request, response);
    }
    private void createApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        boolean isValidApplication = true;
        
        Application application = new Application();
        
        System.out.println("createApplication firstName");
        String firstName = request.getParameter("firstName");
        if(firstName == null || firstName.isBlank())
        {
            application.setFirstNameError("First name cannot be blank");
            isValidApplication =false;
        }else{
            application.setFirstName(firstName);  
        }
        
        System.out.println("createApplication lastName");
        String lastName = request.getParameter("lastName");
        if(lastName == null || lastName.isBlank())
        {
            application.setLastNameError("Last name cannot be blank");
            isValidApplication =false;
        }else{
            application.setLastName(lastName);
        }
        
        System.out.println("createApplication email");
        String email = request.getParameter("email");
        if(email == null || email.isBlank())
        {
            application.setEmailError("Email cannot be blank");
            isValidApplication =false;
        }else{
            application.setEmail(email);
        }
        
        System.out.println("createApplication phone");
        String phone = request.getParameter("phone");
        if(email == null || email.isBlank())
        {
            application.setPhoneError("Phone cannot be blank");
            isValidApplication =false;
        }else{
            application.setPhone(phone);
        }
        
        System.out.println("createApplication date");
        String startDate = request.getParameter("earliestStartDate");
        System.out.println(startDate);
        if(startDate == null || startDate.isBlank())
        {
            application.setStartDateError("StartDate cannot be blank");
            isValidApplication =false;
        }else{
            try{
                LocalDate earliestStartDate = LocalDate.parse(startDate);
                        if(earliestStartDate.isBefore(LocalDate.now())){
            isValidApplication = false;
            application.setStartDateError("Date must not be in the past");
        }
        else{
            application.setEarliestStartDate(earliestStartDate);
        }
            }catch(Exception e){
                application.setStartDateError("StartDate input was bad");
            }
        }
        

        
        System.out.println("createApplication desiredSalary");
        try{
                double desiredSalary = Double.parseDouble(request.getParameter("salary"));
        if(desiredSalary < 1)
        {
            application.setSalaryError("Desired Salary too low");
            isValidApplication =false;
        }
        else{
            application.setDesiredSalary(desiredSalary);
        }
        }catch(Exception e){
            application.setSalaryError("Desired Salary could not be read as a number");
        }

        
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        if(isValidApplication){
            int id = applications.size() + 1;            
            Instant dateTimeSubmitted = Instant.now();
            String jobTitle = request.getParameter("jobTitle");
            application.setJobTitle(jobTitle);
            application.setId(id);
            application.setJobId(jobId);
            application.setDateTimeSubmitted(dateTimeSubmitted);
            application.setActive(true);

            Part filePart = request.getPart("file1");
            System.out.println(filePart);
            if (filePart != null && filePart.getSize() > 0) {
                Attachment attachment = processAttachment(filePart);
                if (attachment != null) {
                    application.setResumeUpload(attachment);
                }
            }
            System.out.println("fail");
            synchronized (this) {
                applications.add(application);
            }
            
            System.out.println("redirecting to applications list");
            request.setAttribute("jobID", jobId);
            request.getRequestDispatcher("/WEB-INF/jsp/view/applicationSuccess.jsp").forward(request, response);
        }else{
            request.setAttribute("Application", application);
            //System.out.println("Application error, sending back to job page");
            //response.sendRedirect("jobs?id="+jobId);
            request.getRequestDispatcher("jobs?id="+jobId).forward(request, response);
        }
        
            
        
        //response.sendRedirect("tickets?action=view&ticketId=" + id);
    }
    
    private Attachment processAttachment(Part filePart) throws IOException {
        System.out.println("In process attachment");
        Attachment attachment = new Attachment();
        try (InputStream inputStream = filePart.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            attachment.setName(filePart.getSubmittedFileName());
            attachment.setContents(outputStream.toByteArray());
        }
        return attachment;
    }

}
