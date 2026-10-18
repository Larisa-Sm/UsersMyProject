package users.usersmyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import users.usersmyproject.dto.UserRequest;
import users.usersmyproject.dto.UserResponse;
import users.usersmyproject.entity.User;
import users.usersmyproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    @Override
    public UserResponse save(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Пользователь с email " + request.getEmail() + " уже существует"
            );
        }

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        User saved = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setSurname(saved.getSurname());
        response.setEmail(saved.getEmail());
        response.setRole(saved.getRole());
        return response;
    }


    @Override
    public Optional<UserResponse> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setName(user.getName());
                    response.setSurname(user.getSurname());
                    response.setEmail(user.getEmail());
                    response.setRole(user.getRole());
                    return response;
                });
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с id " + id + " не найден"));

        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User saved = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setSurname(saved.getSurname());
        response.setEmail(saved.getEmail());
        response.setRole(saved.getRole());
        return response;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с id " + id + " не найден"));
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setName(user.getName());
                    response.setSurname(user.getSurname());
                    response.setEmail(user.getEmail());
                    response.setRole(user.getRole());
                    return response;
                })
                .toList();
    }
}
