package com.example.event.contoller;

import com.example.event.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.event.model.Admin;
import com.example.event.service.AdminService;

@RestController
@RequestMapping("/api/auth")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService; // Inject UserService
    @PostMapping("/adminregister")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {

            // Save the admin
            Admin savedAdmin = adminService.registerAdmin(admin);
            return ResponseEntity.ok(savedAdmin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }
    @GetMapping("/admin/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        return adminService.getAdminByEmail(email);
    }
}