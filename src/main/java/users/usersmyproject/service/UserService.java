package users.usersmyproject.service;


import jakarta.validation.Valid;
import users.usersmyproject.dto.UserRequest;
import users.usersmyproject.dto.UserResponse;
import users.usersmyproject.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {


    UserResponse save(UserRequest request);

    Optional<UserResponse> findById(Long id);

    UserResponse update(Long id, UserRequest request);

    void deleteById(Long id);

    List<UserResponse> findAll();


}
