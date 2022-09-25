package com.example.pagination.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDto implements Serializable {

    @NotNull
    @Size(max= 20, message = "max character limit is 20")
    private String firstName;
    @NotNull
    @Size(max= 20, message = "max character limit is 20")
    private String lastName;
    private int age;
}
