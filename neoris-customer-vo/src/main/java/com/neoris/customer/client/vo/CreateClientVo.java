package com.neoris.customer.client.vo;

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
public class CreateClientVo {
    @NotBlank
    private String password;
    @NotBlank
    private Long identityNumber;
    private String status;
    private Long createdByUser;
    private Date lastModifiedDate;
    private String createdFromIp;
    private String updatedFromIp;

}
