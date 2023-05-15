package com.mcss.simpleapi.Service.Abstracts;

import com.mcss.simpleapi.DTO.CreateUser;
import com.mcss.simpleapi.DTO.ResponseUser;
import com.mcss.simpleapi.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<ResponseUser>> getAllUser();
    ResponseEntity<ResponseUser> getUser(Long id);
    void deleteUser(Long id);
    void addUser(CreateUser user);

}
