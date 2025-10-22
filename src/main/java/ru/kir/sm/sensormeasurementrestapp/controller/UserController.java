package ru.kir.sm.sensormeasurementrestapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kir.sm.sensormeasurementrestapp.dto.UserDto;
import ru.kir.sm.sensormeasurementrestapp.security.UserDetailsImpl;
import ru.kir.sm.sensormeasurementrestapp.service.UserService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }


    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "Logged out successfully";
    }


    @GetMapping("/auth")
    public String getUserDetails() {
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("UserDetails: " + userDetails.getUsername());
        System.out.println("UserDetails: " + userDetails.getPassword());

        return  userDetails.user().toString();
    }
}
