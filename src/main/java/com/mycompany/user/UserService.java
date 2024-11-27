package com.mycompany.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public boolean authenticateUser(String username, String password) {
        // Tìm kiếm người dùng theo username
        Optional<User> userOptional = repo.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Kiểm tra mật khẩu, ở đây ví dụ so sánh trực tiếp
            // Bạn nên mã hóa mật khẩu và so sánh với mật khẩu đã mã hóa trong cơ sở dữ liệu
            return password.equals(user.getPassword());
        }
        return false;
    }
    public String getUserRole(String username) {
        Optional<User> userOptional = repo.findByUsername(username);
        return userOptional.map(User::getRole).orElse(null);
    }

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }
    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any airport with ID" +id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could not find any airport with ID" +id);
        }
        repo.deleteById(id);
    }

}
