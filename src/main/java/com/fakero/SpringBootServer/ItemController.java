/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakero.SpringBootServer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author fakero
 */
@Controller
public class ItemController {
    @Autowired
    private ItemRepository mItemRepository;
    
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("list", mItemRepository.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String get(Model model, @PathVariable String id) {
        try {
            Item checkedItem = mItemRepository.getOne(Long.parseLong(id));
            checkedItem.addToChecked();
            mItemRepository.saveAndFlush(checkedItem);
            model.addAttribute("item", checkedItem);
            return "item";
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/";
        }
    }
    
    @PostMapping("/")
    public String add(@RequestParam String name, @RequestParam String comment){
        name = name.trim();
        if(!name.isEmpty()){
            comment = comment.trim();
            Item newItem = new Item(name, comment);
            mItemRepository.saveAndFlush(newItem);
        }
        return "redirect:/";
    }
    
    @DeleteMapping("/{id}")
    public String remove(Model model, @PathVariable String id){
        try {
            mItemRepository.delete(Long.parseLong(id));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
    
    @GetMapping("/search/{text}")
    public String search(Model model, @PathVariable String text){
        List<Item> foundItems = new ArrayList();
        for(Item i : mItemRepository.findAll()){
            if(i.getName().toLowerCase().contains(text) || i.getComment().toLowerCase().contains(text)){
                foundItems.add(i);
            }
        }
        if(!foundItems.isEmpty()){
            model.addAttribute("found", foundItems);
        }
        return "search";
    }
}
