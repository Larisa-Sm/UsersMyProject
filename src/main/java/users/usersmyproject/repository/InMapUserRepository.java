package users.usersmyproject.repository;


import org.springframework.stereotype.Repository;
import users.usersmyproject.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMapUserRepository implements UserRepository{

    private final Map<Long, User> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public User save(User user){
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        storage.put(user.getId(), user);
        return user;
    }


    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }


    @Override
    public User update(Long id, User user){
        return storage.put(id, user);
    }

    @Override
    public void deleteById(Long id){
        storage.remove(id);
    }

    @Override
    public List<User> findAll(){
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean existsByEmail(String email) {
        return storage.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
}
