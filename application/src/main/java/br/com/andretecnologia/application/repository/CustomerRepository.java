package br.com.andretecnologia.application.repository;

import br.com.andretecnologia.application.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@CrossOrigin
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<Customer>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM CUSTOMER");

        for (Map<String, Object> row : rows) {
            Customer customer = new Customer();

            customer.setId((int) row.get("ID"));
            customer.setFirstName((String) row.get("FIRST_NAME"));
            customer.setLastName((String) row.get("LAST_NAME"));

            customers.add(customer);
        }

        return customers;

    }
}
