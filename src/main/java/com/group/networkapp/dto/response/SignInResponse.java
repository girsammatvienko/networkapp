package com.group.networkapp.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignInResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
