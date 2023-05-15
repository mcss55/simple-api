package com.mcss.simpleapi.Service.Concretes;

import com.mcss.simpleapi.DTO.CreateUser;
import com.mcss.simpleapi.DTO.ResponseUser;
import com.mcss.simpleapi.Exceptions.UserNotFoundException;
import com.mcss.simpleapi.Mapper.SimpleMapper;
import com.mcss.simpleapi.Model.User;
import com.mcss.simpleapi.Repository.UserRepository;
import com.mcss.simpleapi.Service.Abstracts.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    final SimpleMapper simpleMapper;

    @Override
    public ResponseEntity<List<ResponseUser>> getAllUser() {
        return ResponseEntity.ok(userRepository.findAll().stream()
                .map(simpleMapper::userToResponse).toList());
    }

    @Override
    public ResponseEntity<ResponseUser> getUser(Long id) {
        ResponseUser user = simpleMapper.userToResponse(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
        return ResponseEntity.ok(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
    }

    @Override
    public void addUser(CreateUser user) {
        User createdUser = simpleMapper.createUserToUser(user);
        userRepository.save(createdUser);
    }
}
