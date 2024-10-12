package Graduated.Task.C2C.User.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class userInfoDto {
    private Long userId;
    private String username;
    private String id;
    private LocalDateTime joinedDate;
}
