/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakero.SpringBootServer;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author fakero
 */
@Entity
public class Item extends AbstractPersistable<Long> {
    
    private String name;
    private String comment;
    private Integer checked;
    
    public Item(){
        
    }
    
    public Item(String name, String comment){
        this.name = name;
        this.comment = comment;
        this.checked = 0;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getComment(){
        return this.comment;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    
    public int getChecked(){
        return this.checked;
    }
    
    public void setChecked(int checked){
        this.checked = checked;
    }
    
    public void addToChecked(){
        this.checked++;
    }
}
