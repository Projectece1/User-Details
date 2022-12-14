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
import org.springframework.web.multipart.MultipartFile;
import java.com.user.repository.UserRepository;

@RestController
public class UserController {
    
    private static final Logger logger = LogManager.getLogger(UserController.class)

    @Autowired
    private UserRepository userRepository;

    //Get the user details for the given userId
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable long id,@Param String userName, @Param String password) {
    Optional<User> registeredUser = userRepository.findById(id);
        if (updatedUser.isEmpty()) {
            logger.debug("User not found in the data base");
            return ResponseEntity.notFound().build();
        }
            
        if(!(registeredUser.getUserName().equals(user.getUserName()) && registeredUser.getPassword().equals(user.getPassword()))){
            logger.debug("User provided invalid username and password");
            throw InCorrectCredentialsException("Invalid username and password..");
         }

        return user.get();
    }

    @DeleteMapping("/users/{id}")
    public void removeUser(@PathVariable long id,@Param String userName, @Param String password) {
    
    Optional<User> registeredUser = userRepository.findById(id);
        if (updatedUser.isEmpty()) {
            logger.debug("User not found in the data base");
            return ResponseEntity.notFound().build();
        }
            
        if(!(registeredUser.getUserName().equals(user.getUserName()) && registeredUser.getPassword().equals(user.getPassword()))){
        {
          logger.debug("User provided invalid username and password");
          throw InCorrectCredentialsException("Incorrect username and password..");
         }
        userRepository.deleteById(id);
    }

    // Register new user
    @PostMapping("/users/register")
    public ResponseEntity<Object> registerNewUser(@RequestBody User user) {
        try{
            User savedUser = userRepository.save(user);
        }catch(Exception e) {
            logger.debug("Got exception while updating the data base"+ e.toString());
            return new ResponseEntity<>("Couldn't register the user due to internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Successfully registered the user"), HttpStatus.OK);
    }

    //Update the user details
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUserDetails(@RequestBody User user, @PathVariable long id) {

        //Fetching user details from the data base
        Optional<User> registeredUser = userRepository.findById(id);
        if (updatedUser.isEmpty()) {
            logger.debug("User not found in the data base");
            return ResponseEntity.notFound().build();
        }
            
        if(!(registeredUser.getUserName().equals(user.getUserName()) && registeredUser.getPassword().equals(user.getPassword()))){
          logger.debug("User provided invalid username and password");
          throw InCorrectCredentialsException("Incorrect username and password);
         }
        user.setId(id);
        userRepository.save(user);
        return return new ResponseEntity<>("Successfully updated the User details"), HttpStatus.OK);
    }
    //Upload User Imgae 
    @PostMapping("/upload-image")
    public ResponseEntity<FileResponse> uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("id") Long id) {
    try{
        //Upload image to Imgur server
        service.uploadImage(image.getOriginalFilename(), id);
        //Fetch user
        Optional<User> registeredUser = userRepository.findById(id);
        if (updatedUser.isEmpty())
            return ResponseEntity.notFound().build();
        user.setId(id);
        user.setImage(image.getOriginalFilename());
        userRepository.save(user);
        }catch(Exception e){
        logger.debug("Got exception while uploading image to the Imgur client"+ e.toString()+ e.stackTrace());
        return new ResponseEntity<>("Image is not uploaded due to internal error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
        return new ResponseEntity<>("Image successfully uploaded", HttpStatus.OK);

    }
}
