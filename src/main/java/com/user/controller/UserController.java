package java.com.user.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.com.user.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable long id,@Param String userName, @Param String password) {
    Optional<User> registeredUser = userRepository.findById(id);
        if (updatedUser.isEmpty())
            return ResponseEntity.notFound().build();
            
        if(!(registeredUser.getUserName().equals(user.getUserName()) && registeredUser.getPassword().equals(user.getPassword()))){
        {
          throw InCorrectCredentialsException("Invalid username and password..");
         }

        return user.get();
    }

    @DeleteMapping("/users/{id}")
    public void removeUser(@PathVariable long id,@Param String userName, @Param String password) {
    
    Optional<User> registeredUser = userRepository.findById(id);
        if (updatedUser.isEmpty())
            return ResponseEntity.notFound().build();
            
        if(!(registeredUser.getUserName().equals(user.getUserName()) && registeredUser.getPassword().equals(user.getPassword()))){
        {
          throw InCorrectCredentialsException("Incorrect username and password);
         }
        userRepository.deleteById(id);
    }

    @PostMapping("/users/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUserDetails(@RequestBody User user, @PathVariable long id) {

        Optional<User> registeredUser = userRepository.findById(id);
        if (updatedUser.isEmpty())
            return ResponseEntity.notFound().build();
            
        if(!(registeredUser.getUserName().equals(user.getUserName()) && registeredUser.getPassword().equals(user.getPassword()))){
        {
          throw InCorrectCredentialsException("Incorrect username and password);
         }
        

        user.setId(id);

        userRepository.save(student);

        return ResponseEntity.noContent().build();
    }
}
