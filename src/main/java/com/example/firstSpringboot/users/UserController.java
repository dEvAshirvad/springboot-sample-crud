package com.example.firstSpringboot.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserServices userServices;
    @Autowired
    public UserController(UserServices studentService) {
        this.userServices = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Users> listOfUsers = userServices.getUsers();
        map.put("status", true);
        map.put("data", listOfUsers);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody Users users) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Users createdUser = userServices.addNew(users);
        map.put("status", true);
        map.put("data", createdUser);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") UUID id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        userServices.deleteUserById(id);
        map.put("status", true);
        map.put("data", "User Deleted");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable("userId") UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Users user = userServices.updateUser(id, name, email);
        map.put("status", true);
        map.put("data", user);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
