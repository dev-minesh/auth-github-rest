package com.github.auth.demo.viewModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTokenResponseType {
    String access_token;
    String scope;
    String token_type;
}
