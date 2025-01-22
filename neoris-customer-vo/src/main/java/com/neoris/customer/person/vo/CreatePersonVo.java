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
public class CreatePersonVo {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private Date birthDate;

    @NotBlank
    private Long identityNumber;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String gender;
    private String status;
    private Long createdByUser;
    private Date lastModifiedDate;
    private String createdFromIp;
    private String updatedFromIp;
}
