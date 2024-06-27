package com.apiTPO.technologyHouse.app.controllers;

import com.apiTPO.technologyHouse.app.dtos.UserIdDTO;
import com.apiTPO.technologyHouse.app.models.Category;
import com.apiTPO.technologyHouse.app.models.User;
import com.apiTPO.technologyHouse.app.repositories.UserRepository;
import com.apiTPO.technologyHouse.app.services.CategoryService;
import com.apiTPO.technologyHouse.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody UserIdDTO userIdDTO) {
        return ResponseEntity.ok(userService.delete(userIdDTO));
    }

}
