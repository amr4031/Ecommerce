package com.ecommerce.ecommerce.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LoginDto {
    private String UserName;
    private String UserPassword;
}
