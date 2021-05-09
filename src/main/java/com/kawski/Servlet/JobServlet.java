/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kawski.Servlet;

import com.kawski.Models.Application;
import com.kawski.Models.Job;
import com.kawski.Models.Pagination;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jkaws
 */
public class JobServlet extends HttpServlet {

    private static final SortedSet<Job> jobs = new TreeSet();
    private Pagination pagination;

    private void InitializeJobs()
    {       
        ServletContext context = getServletContext();
        String path = context.getRealPath("WEB-INF/csv/job_data.csv");
        System.out.println(path);
        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            System.out.println("**** INSIDE TRY BLOCK****");
            String line;
        
            while((line = reader.readLine()) != null)
            {
                String[] jobProps = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                Job job = new Job
                        (
                                Integer.parseInt(jobProps[0]),        //id
                                Boolean.parseBoolean(jobProps[1]),    //active
                                LocalDate.parse(jobProps[2]),         //dateCreated
                                jobProps[3],                          //title
                                jobProps[4],                          //city
                                jobProps[5],                          //state
                                Boolean.parseBoolean(jobProps[6]),    //isFullTime
                                jobProps[7],                          //department
                                jobProps[8],                          //experience
                                jobProps[9],                          //wageCatergory
                                Double.parseDouble(jobProps[10]),     //salary
                                jobProps[11]                          //jobDescription
                        );
                jobs.add(job);
                System.out.println(job);
            }            
        }
        catch(IOException e)
        {
            System.out.println("****FAILED LOADING CSV FILE*****");
        }   
    }
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
        if(jobs.isEmpty())
        {
            InitializeJobs();
        }
        if(request.getAttribute("Pagination") == null)
        {
            pagination = new Pagination(4, jobs.size(), 1);
        }
        else
        {
            pagination = (Pagination)request.getAttribute("Pagination");
        }
        String pageStr = request.getParameter("page");
        if(pageStr != null && !pageStr.equals("")) 
        {
            try 
            {
                pagination.setCurrentPage(Integer.parseInt(pageStr));                
            } 
            catch(NumberFormatException e) 
            {
                pagination.setCurrentPage(1);
            }
        }
        if(request.getAttribute("Application")==null){
            request.setAttribute("Application", new Application());
        }
        
        request.setAttribute("Pagination", pagination);
        request.setAttribute("Jobs", jobs);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.equals("")) {
            try 
            {
                int id = Integer.parseInt(idStr);
                for(Job job : jobs)
                {
                    if(job.getId() == id)
                    {
                        request.setAttribute("Job", job);
                        request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
                        break;
                    }
                }
            } 
            catch(NumberFormatException e) 
            {
                pagination.setCurrentPage(1);
            }
        }
        request.getRequestDispatcher("/WEB-INF/jsp/view/jobList.jsp").forward(request, response);
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
        processRequest(request, response);
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.equals("")) {
            try 
            {
                int id = Integer.parseInt(idStr);
                for(Job job : jobs)
                {
                    if(job.getId() == id)
                    {
                        request.setAttribute("Job", job);
                        request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
                        break;
                    }
                }
            } 
            catch(NumberFormatException e) 
            {
                pagination.setCurrentPage(1);
            }
        }
        request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
