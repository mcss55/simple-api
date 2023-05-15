package com.mcss.simpleapi.Controller;

import com.mcss.simpleapi.DTO.CreateUser;
import com.mcss.simpleapi.Service.Abstracts.UserService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser() {return userService.getAllUser();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {return userService.getUser(id);}

    @PostMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void addUser(@RequestBody CreateUser createUser) {userService.addUser(createUser);}

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {userService.deleteUser(id);}

}
