package org.basic.spring.security.rest.controller;

import java.security.Principal;

import org.basic.spring.security.rest.dto.entity.user.AuthenticationDto;
import org.basic.spring.security.rest.dto.entity.user.UserDto;
import org.basic.spring.security.rest.dto.entity.user.UserList;
import org.basic.spring.security.rest.dto.response.AbstractResponseDto;
import org.basic.spring.security.rest.service.UserManagedService;
import org.basic.spring.security.rest.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
    @Autowired
    UserManagedService userService;  //Service which will do all data retrieval/manipulation work
 
    //-------------------User Profile Detail--------------------------------------------------------
    
    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public HttpEntity<AbstractResponseDto> profileDetail(Principal principal) {
    	UserDto user = userService.findByUsername(principal.getName());
        return ResponseUtil.success().body(user).send(HttpStatus.OK);
    }
    
    //-------------------Count user--------------------------------------------------------
    
    @RequestMapping(value = "/user/count", method = RequestMethod.GET)
    public HttpEntity<AbstractResponseDto> userCout() {
    	
        return ResponseUtil.success().body(userService.count()).send(HttpStatus.OK);
    }
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public HttpEntity<AbstractResponseDto> listAllUsers() {
        UserList users = userService.findAllUsers();
        if(users.getUserList().isEmpty()){
            return ResponseUtil.error().message("No data found").send(HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.success().body(users).message("User list fetched successfully !!!").send(HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<AbstractResponseDto> getUser(@PathVariable("id") long id) {
        UserDto user = userService.findById(id);
        if (user == null) {
        	return ResponseUtil.error().message("No data found").send(HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.success().body(user).message("User fetched successfully !!!").send(HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<AbstractResponseDto> createUser(@RequestBody AuthenticationDto user) {
 
        userService.saveUser(user);
 
        return ResponseUtil.success().body(user.detail()).message("User created successfully !!!").send(HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AbstractResponseDto> updateUser(@PathVariable("id") long id, @RequestBody UserDto user) {
         
        UserDto currentUser = userService.findById(id);
         
        if (currentUser==null) {
            return ResponseUtil.error().message("User with id " + id + " not found").send(HttpStatus.NOT_FOUND);
        }
        
        userService.updateUser(currentUser, user);
        return ResponseUtil.success().body(currentUser).message("User updated successfully !!!").send(HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AbstractResponseDto> deleteUser(@PathVariable("id") long id) {
 
        UserDto user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return ResponseUtil.error().message("Unable to delete. User with id " + id + " not found").send(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return ResponseUtil.success().body(user).message("User deleted successfully !!!").send(HttpStatus.OK);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<AbstractResponseDto> deleteAllUsers() {
 
        userService.deleteAllUsers();
        return ResponseUtil.success().body(new UserList()).message("All User deleted successfully !!!").send(HttpStatus.OK);
    }
}
