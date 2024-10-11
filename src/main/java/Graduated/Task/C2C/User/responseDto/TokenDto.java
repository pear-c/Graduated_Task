package Graduated.Task.C2C.User.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {
    String accessToken;
    String message;
}
