package users.usersmyproject;

import org.springframework.boot.SpringApplication;

public class TestUsersMyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.from(UsersMyProjectApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
