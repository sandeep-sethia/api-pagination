package com.example.pagination.dto;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto implements Serializable {
    int offset;
    int limit;
    boolean isLastRecord;
    List<UserEntityDto> items;
}
