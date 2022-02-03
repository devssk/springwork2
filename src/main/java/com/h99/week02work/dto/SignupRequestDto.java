package com.h99.week02work.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해 주세요")
    @Pattern(regexp = "[a-zA-Z0-9]{3,12}", message = "아이디는 영문, 숫자만 가능하며 3 ~ 12자리 까지 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]{4,16}", message = "비밀번호는 영문,숫자만 가능하며 4 ~ 16자리 까지 가능합니다.")
    private String password;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]{4,16}", message = "비밀번호는 영문,숫자만 가능하며 4 ~ 16자리 까지 가능합니다.")
    private String passwordCheck;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
}
