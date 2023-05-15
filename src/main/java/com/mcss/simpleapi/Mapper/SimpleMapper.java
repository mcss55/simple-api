package com.mcss.simpleapi.Mapper;

import com.mcss.simpleapi.DTO.CreateUser;
import com.mcss.simpleapi.DTO.ResponseUser;
import com.mcss.simpleapi.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SimpleMapper {

    ResponseUser userToResponse(User user);
    User createUserToUser(CreateUser user);

}
