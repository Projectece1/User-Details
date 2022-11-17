package java.com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootUserApplication {

    @Autowired
    StudentDataRestRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUserApplication.class, args);
    }
}
