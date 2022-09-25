package com.example.pagination.services;

import com.example.pagination.dto.UserEntityDto;
import com.example.pagination.dto.UserResponseDto;
import com.example.pagination.entities.UserEntity;
import com.example.pagination.entities.UserEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public UserResponseDto getUserInfoByUserName(String firstName, int limit, int offset) {
        Slice<UserEntity> userEntities = userEntityRepository.findByFirstNameStartsWithIgnoreCase(firstName, PageRequest.of(offset, limit));
        List<UserEntityDto> items = userEntities.stream()
                .map(this::convertUserEntityToUserDto).collect(Collectors.toList());
        return UserResponseDto.builder()
                .limit(limit)
                .offset(offset)
                .isLastRecord(!userEntities.hasNext())
                .items(items)
                .build();
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
