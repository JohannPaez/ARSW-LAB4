/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
	
	@Autowired
	BlueprintsServices bps;
        
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllBlueprints(){
	    try {
	        //obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(bps.getAllBlueprints(),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error, No blueprints found",HttpStatus.NOT_FOUND);
	    }        
	}
    	
	@RequestMapping(value = "/{author}", method = RequestMethod.GET)
	//@GetMapping("/{author}")
	public ResponseEntity<?> getAllBlueprintsByAuthor(@PathVariable String author){
	    try {
	        //obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(bps.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error, No blueprints found",HttpStatus.NOT_FOUND);
	    }        
	}	
    
	
	@GetMapping("/{author}/{bpname}")
	public ResponseEntity<?> getAllBlueprintsByAuthor(@PathVariable String author, @PathVariable String bpname){
	    try {
	        //obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(bps.getBlueprint(author, bpname),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error, No blueprints found",HttpStatus.NOT_FOUND);
	    }        
	}	
    
	
	@RequestMapping(method = RequestMethod.POST)	
	public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody Blueprint bp){
	    	boolean flag = false;
	        //registrar Blueprint
			try {
				bps.addNewBlueprint(bp);
				flag = true;
			} catch (BlueprintPersistenceException e) {
				 return new ResponseEntity<>("Error, No blueprints add",HttpStatus.NOT_FOUND);
			}
	        return new ResponseEntity<>(flag, HttpStatus.CREATED);
	}
    
	
	@PutMapping(value = "/{author}/{bpname}")
	public ResponseEntity<?> getAllBlueprintsByAuthorAndPut(@PathVariable String author, @PathVariable String bpname, @RequestBody Blueprint bp){
		boolean flag = false;
		try {
			Blueprint b = bps.getBlueprint(author, bpname);
			b.setPoints(bp.getPoints());
			flag = true;
		} catch (BlueprintNotFoundException e) {
			 return new ResponseEntity<>("Error, No blueprints put",HttpStatus.NOT_FOUND);
		}	      
	    return new ResponseEntity<>(flag, HttpStatus.ACCEPTED);
	}	
}

