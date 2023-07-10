package com.inventoryManagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdminRequestDto {
    private String email;

    private String password;

}
