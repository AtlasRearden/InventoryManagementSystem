package com.inventoryManagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdminResponseDto {

    private String jwtToken;
    private String email;

}
