package br.com.andretecnologia.application.controller;

import br.com.andretecnologia.application.domain.Customer;
import br.com.andretecnologia.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping
    public ResponseEntity < List<Customer> > findAll () {

        return ResponseEntity.ok(service.findAll());

    }
}
