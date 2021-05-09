/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kawski.Models;

import java.io.Serializable;

/**
 *
 * @author jkaws
 */
public class Pagination implements Serializable {
    int currentPage;
    int itemsPerPage;
    int maxPages;
    int firstIndexOnPage;
    int lastIndexOnPage;
    
    public Pagination(){}
    public Pagination(int itemsPerPage, int itemsSize, int currentPage)
    {
        this.itemsPerPage = itemsPerPage;
        maxPages = itemsSize / itemsPerPage;
        if(itemsSize % itemsPerPage != 0) {maxPages++;} 
        this.currentPage = currentPage;
    }
    
    public void setCurrentPage(int currentPage)
    {
        if(currentPage < 1)
        {
            currentPage = 1;
        } 
        else if(currentPage > maxPages) 
        {
            currentPage = maxPages;
        }
        this.currentPage = currentPage;
    }
    public int getCurrentPage(){return currentPage;}
    
    public void setItemsPerPage(int itemsPerPage){this.itemsPerPage = itemsPerPage;}
    public int getItemsPerPage(){return itemsPerPage;}
    
    //private void setMaxPages(int maxPages){this.maxPages = maxPages;}
    public int getMaxPages(){return maxPages;}
    
    //private void setFirstIndexOnPage(int firstItemOnPage){this.firstIndexOnPage = firstIndexOnPage;}
    public int getFirstIndexOnPage()
    {
        firstIndexOnPage = (currentPage - 1) * itemsPerPage;
        return firstIndexOnPage;
    }
    
    //private void setLastIndexOnPage(int lastItemOnPage){this.lastIndexOnPage = lastIndexOnPage;}
    public int getLastIndexOnPage()
    {
        lastIndexOnPage = firstIndexOnPage + itemsPerPage -1;
        return lastIndexOnPage;
    }
    
}
