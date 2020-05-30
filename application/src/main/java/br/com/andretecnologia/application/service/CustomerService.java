package br.com.andretecnologia.application.service;

import br.com.andretecnologia.application.domain.Customer;
import br.com.andretecnologia.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

}
