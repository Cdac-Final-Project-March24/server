package com.app.dto;

import com.app.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserRequestDto {
    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private Address address;
}
