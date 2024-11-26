package com.mycompany.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRespository repo;

    public List<Admin> listAll() {
        return (List<Admin>) repo.findAll();
    }
    public void save(Admin admin) {
        repo.save(admin);
    }

    public Admin get(Integer id) throws AdminNotFoundException {
        Optional<Admin> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new AdminNotFoundException("Could not find any users with ID" +id);
    }

    public void delete(Integer id) throws AdminNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new AdminNotFoundException("Could not find any admin with ID" +id);
        }
        repo.deleteById(id);
    }
}
