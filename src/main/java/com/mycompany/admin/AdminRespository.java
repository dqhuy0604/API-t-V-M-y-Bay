package com.mycompany.admin;

import org.springframework.data.repository.CrudRepository;

public interface AdminRespository extends CrudRepository<Admin, Integer> {
    public Long countById(Integer id);

}
