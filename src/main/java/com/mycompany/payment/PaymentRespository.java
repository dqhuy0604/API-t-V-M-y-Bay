package com.mycompany.payment;

import org.springframework.data.repository.CrudRepository;

public interface PaymentRespository extends CrudRepository<Payment, Integer> {

    public Long countById(Integer id);
}
