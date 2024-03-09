package com.example.event.contoller;

import com.example.event.model.Register;
import com.example.event.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registers")
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<Register> saveRegister(@RequestBody Register register) {
        Register newRegister = registerService.saveRegister(register);
        return ResponseEntity.ok(newRegister);
    }

    @GetMapping
    public List<Register> getAllRegisters() {
        return registerService.findAllRegisters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Register> getRegisterById(@PathVariable Long id) {
        return registerService.findRegisterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegister(@PathVariable Long id) {
        registerService.deleteRegister(id);
        return ResponseEntity.ok().build();
    }
}