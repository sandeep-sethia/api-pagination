package com.example.pagination.services;

import com.example.pagination.dto.UserEntityDto;
import com.example.pagination.entities.UserEntity;
import com.example.pagination.entities.UserEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    ModelMapper modelMapper;

    public UserEntityDto getInfo(long id) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(id);
        return userEntity.map(this::convertUserEntityToUserDto).orElse(null);
    }

    public String saveUsers(UserEntityDto userRequest) {
        UserEntity userEntity = userEntityRepository.save(UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .age(userRequest.getAge())
                .build());
        return "saved";
    }

    private UserEntityDto convertUserEntityToUserDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserEntityDto.class);
    }
}
