package com.app.dto;

import com.app.entity.Address;
import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private Address address;
}
