package users.usersmyproject.repository;

import users.usersmyproject.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    User update(Long id, User user);

    void deleteById(Long id);

    List<User> findAll();

    boolean existsByEmail(String email);
}
