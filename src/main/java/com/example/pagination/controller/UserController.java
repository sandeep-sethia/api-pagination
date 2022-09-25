package com.example.pagination.controller;

import com.example.pagination.dto.UserEntityDto;
import com.example.pagination.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Get user Information by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntityDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping("/users/info/{id}")
    public ResponseEntity<UserEntityDto> getUserInfo(
            @PathVariable long id
    ) {
        return ResponseEntity.ok().body(userService.getInfo(id));
    }

    @PostMapping("/users/info")
    public ResponseEntity<String> saveUser(
            @RequestBody UserEntityDto userRequest
    ) {
        return ResponseEntity.ok().body(userService.saveUsers(userRequest));
    }
}
