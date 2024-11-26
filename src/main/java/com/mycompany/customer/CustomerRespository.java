package com.mycompany.customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRespository extends CrudRepository<Customer, Integer> {
    public Long countById(Integer id);
}
