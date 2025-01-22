package com.neoris.customer.person.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonVo {
    private String name;
    private String surname;
    private Date birthDate;
    private Long identityNumber;
    private String address;
    private String phone;
    private String status;
}
