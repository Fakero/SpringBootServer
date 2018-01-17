/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakero.SpringBootServer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fakero
 */
@RestController
@RequestMapping("rest")
public class ItemRestController {
    
    @Autowired
    private ItemRepository mItemRepository;
    
    @GetMapping("/")
    public List<Item> getItems(){
        return mItemRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Item getItem(@PathVariable String id){
        return mItemRepository.findOne(Long.valueOf(id));
    }
    
    @DeleteMapping("/{id}")
    public Item deleteItem(@PathVariable String id){
        Item deleted = mItemRepository.findOne(Long.valueOf(id));
        mItemRepository.delete(deleted);
        return deleted;
    }
    
    @PostMapping("/")
    public Item postItem(@RequestBody Item newBook){
        mItemRepository.saveAndFlush(newBook);
        return newBook;
    }
}
