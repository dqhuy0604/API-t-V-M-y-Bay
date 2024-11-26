package com.mycompany;

import com.mycompany.admin.Admin;
import com.mycompany.admin.AdminRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AdminRepositoryTests {
    @Autowired
    private AdminRespository repo;

    @Test
    public void testAddNew() {
        Admin admin = new Admin();
        admin.setEmail("asdasd@gmail.com");
        admin.setPassword("1234567");
        admin.setPhone("1234567897");

        Admin savedAdmin = repo.save(admin);
        assertNotNull(savedAdmin, "Admin không được null");
        assertTrue(savedAdmin.getId() > 0, "ID phải lớn hơn 0");

    }
    @Test
    public void testListAll() {
        Iterable<Admin> admins = repo.findAll();
        boolean hasElements = admins.iterator().hasNext();
        assertTrue(hasElements, "Danh sách Admin phải có ít nhất 1 phần tử");

        for(Admin admin : admins) {
            System.out.println(admin);

        }
    }

    @Test
    public void testUpdate() {
        Integer adminId = 1;
        Optional<Admin> optionalAdmin = repo.findById(adminId);
        Admin admin = optionalAdmin.get();
        admin.setPassword("123123");
        repo.save(admin);

        Admin updatedAdmin = repo.findById(adminId).get();
        assertEquals("123123", updatedAdmin.getPassword());
    }

    @Test
    public void testGet(){
        Integer adminId=1;
        Optional<Admin> optionalAdmin = repo.findById(adminId);
        System.out.println(optionalAdmin.get());
    }

    @Test
    public void testDelete(){
        Integer adminId =2;
        repo.deleteById(adminId);

        Optional<Admin> optionalAdmin = repo.findById(adminId);
    }
}
