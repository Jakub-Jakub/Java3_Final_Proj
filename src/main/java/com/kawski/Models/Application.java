/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kawski.Models;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author jkaws
 */
public class Application implements Serializable, Comparable
{
    //private instance
    private int id;
    private int jobId;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Attachment resumeUpload;
    private double desiredSalary;
    private LocalDate earliestStartDate;
    private Instant dateTimeSubmitted;
    private boolean active;
    private String firstNameError="", lastNameError="", emailError="", phoneError="", resumeError="", salaryError="", startDateError="";
    //static helpers
    private static final String HAS_DIGIT = ".*\\d+.*";
    
    //constructors
    public Application(){}
    public Application(
        int id,
        int jobId,
        String firstName,
        String lastName,
        String email,
        String phone,
        Attachment resumeUpload,
        double desiredSalary,
        LocalDate earliestStartDate,
        Instant dateTimeSubmitted,
        boolean active
    )
    {
        this.id=id;
        this.jobId=jobId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phone=phone;
        this.resumeUpload=resumeUpload;
        this.desiredSalary=desiredSalary;
        this.earliestStartDate=earliestStartDate;
        this.dateTimeSubmitted=dateTimeSubmitted;
        this.active=active;
    }
    
    // getters and setters
    public void setId(int id){this.id = id;}
    public int getId(){return id;}
    
    public void setJobId(int jobId){this.jobId = jobId;}
    public int getJobId(){return jobId;}
    
    public void setFirstName(String firstName)
    {

            this.firstName = firstName;
    }
    public String getFirstName(){return firstName;}
    
    public void setLastName(String lastName)
    {

            this.lastName = lastName;

    }
    public String getLastName(){return lastName;}
    
    public void setJobTitle(String jobTitle){this.jobTitle = jobTitle;}
    public String getJobTitle(){return jobTitle;}
    
    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}
    
    public void setPhone(String phone){this.phone = phone;}
    public String getPhone(){return phone;}
    
    public void setResumeUpload(Attachment resumeUpload){this.resumeUpload = resumeUpload;}
    public Attachment getResumeUpload(){return resumeUpload;}
    
    public void setDesiredSalary(double desiredSalary){this.desiredSalary = desiredSalary;}
    public double getDesiredSalary(){return desiredSalary;}
    
    public void setEarliestStartDate(LocalDate earliestStartDate){this.earliestStartDate = earliestStartDate;}
    public LocalDate getEarliestStartDate(){return earliestStartDate;}
    
    public void setDateTimeSubmitted(Instant dateTimeSubmitted){this.dateTimeSubmitted = dateTimeSubmitted;}
    public Instant getDateTimeSubmitted(){return dateTimeSubmitted;}
    
    public void setActive(boolean active){this.active = active;}
    public boolean getActive(){return active;}
    
    public void setFirstNameError(String firstNameError){this.firstNameError = firstNameError;}
    public String getFirstNameError(){return firstNameError;}
    
    public void setLastNameError(String lastNameError){this.lastNameError = lastNameError;}
    public String getLastNameError(){return lastNameError;}
    
    public void setEmailError(String emailError){this.emailError = emailError;}
    public String getEmailError(){return emailError;}
    
    public void setPhoneError(String phoneError){this.phoneError = phoneError;}
    public String getPhoneError(){return phoneError;}
    
    public void setResumeError(String resumeError){this.resumeError = resumeError;}
    public String getResumeError(){return resumeError;}
    
    public void setSalaryError(String salaryError){this.salaryError = salaryError;}
    public String getSalaryError(){return salaryError;}
    
    public void setStartDateError(String startDateError){this.startDateError = startDateError;}
    public String getStartDateError(){return startDateError;}
    
    
    @Override
    public String toString(){return "Job Title: "+ jobTitle + " Name:" + firstName + " " + lastName + " Email: " + email;}

    @Override
    public int compareTo(Object o) {
        Application app = (Application)o;
        return dateTimeSubmitted.compareTo(app.dateTimeSubmitted);
    }
    
    public Date getStartDate()
    { 
        //Code solution found in
        //https://stackoverflow.com/questions/33066904/localdate-to-java-util-date-and-vice-versa-simplest-conversion
        
        ZoneId zone = ZoneId.of("America/Chicago");
        ZonedDateTime zdt = earliestStartDate.atStartOfDay(zone);
        Instant instant = zdt.toInstant();
        
        return Date.from(instant);
    }
    
}
