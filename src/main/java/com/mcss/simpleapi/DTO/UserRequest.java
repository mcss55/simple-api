package com.mcss.simpleapi.DTO;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRequest {

    String name;
    String surname;
    String username;
    String password;
    int age;
    String role;

}
