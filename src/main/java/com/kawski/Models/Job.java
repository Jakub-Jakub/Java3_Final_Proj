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

/**
 *
 * @author jkaws
 */
public class Job implements Serializable, Comparable{
    //private variables
    private int id;
    private boolean active;
    private LocalDate dateCreated;
    private String title;
    private String city;
    private String state;
    private boolean fullTime;
    private String department;
    private String experience;
    private String wageCategory;
    private double salary;
    private String jobDescription;
    
    //constructors
    
    public Job()
    {
        this.id = 0;
        this.active = false;
        this.dateCreated = null;
        this.title = "";
        this.city = "";
        this.state = "";
        this.fullTime = false;
        this.department = "";
        this.experience = "";
        this.wageCategory = "";
        this.salary = 0.0;  
        this.jobDescription = "";
    }
    public Job(int id, boolean active, LocalDate date, String title, 
            String city,String state, boolean isFullTime, String department, 
            String experience,String wageCategory, double salary, String jobDescription)
    {
        this.id = id;
        this.active = active;
        this.dateCreated = date;
        this.title = title;
        this.city = city;
        this.state = state;
        this.fullTime = isFullTime;
        this.department = department;
        this.experience = experience;
        this.wageCategory = wageCategory;
        this.salary = salary;  
        this.jobDescription = jobDescription;
    }
    public String getHack(){return "HACK IN PROGRESS";}
    //getters / setters
    public void setId(int id){ this.id = id;}
    public int getId(){ return id;}
    
    public void setActive(boolean active){this.active = active;}
    public boolean getActive(){return active;}
    
    public void setDateCreated(LocalDate date){this.dateCreated = date;}
    public LocalDate getDateCreated(){return dateCreated;}
    
    public void setTitle(String title){this.title = title;}
    public String getTitle(){return title;}
    
    public void setCity(String city){this.city = city;}
    public String getCity(){return city;}
    
    public void setState(String state){this.state = state;}
    public String getState(){return state;}
    
    public void setFullTime(boolean isFullTime){this.fullTime = isFullTime;}
    public boolean getFullTime(){return fullTime;}
    
    public void setDepartment(String department){this.department = department;}
    public String getDepartment(){return department;}
    
    public void setExperience(String experience){this.experience = experience;}
    public String getExperience(){return experience;}
    
    public void setWageCategory(String wageCategory){this.wageCategory = wageCategory;}
    public String getWageCategory(){return wageCategory;}
    
    public void setSalary(double salary){this.salary = salary;}
    public double getSalary(){return salary;}
    
    public void setJobDescription(String jobDescription){this.jobDescription = jobDescription;}
    public String getJobDescription(){return jobDescription;}
    
    //additional methods
    public String getLocation(){return city+", "+state;}
    
    public Date newDateCreated()
    { 
        //Code solution found in
        //https://stackoverflow.com/questions/33066904/localdate-to-java-util-date-and-vice-versa-simplest-conversion
        
        ZoneId zone = ZoneId.of("America/Chicago");
        ZonedDateTime zdt = dateCreated.atStartOfDay(zone);
        Instant instant = zdt.toInstant();
        
        return Date.from(instant);
    }
    @Override
    public String toString()
    {
        return title + "\n" + 
               "Location: " + city + ", " + state + "\n" + 
               "Department: " + department + "\n" + 
               "Active: " + active;
    }
    @Override
    public int compareTo(Object o)
    {
        Job job = (Job)o;
        int compareResult = dateCreated.compareTo(job.dateCreated);
        if(compareResult == 0)
        {
            compareResult = title.compareTo(job.title);
        }
        return compareResult;
    }

}
