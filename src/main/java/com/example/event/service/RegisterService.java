package com.example.event.service;

import com.example.event.model.Register;
import com.example.event.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public Register saveRegister(Register register) {
        return registerRepository.save(register);
    }

    public List<Register> findAllRegisters() {
        return registerRepository.findAll();
    }

    public Optional<Register> findRegisterById(Long id) {
        return registerRepository.findById(id);
    }

    public void deleteRegister(Long id) {
        registerRepository.deleteById(id);
    }
}