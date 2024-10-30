package Graduated.Task.C2C.User.Controller;

import Graduated.Task.C2C.User.Service.UserService;
import Graduated.Task.C2C.User.requestDto.loginDto;
import Graduated.Task.C2C.User.responseDto.TokenDto;
import Graduated.Task.C2C.User.responseDto.userInfoDto;
import Graduated.Task.C2C.core.JwtTokenUtil;
import Graduated.Task.C2C.core.Message;
import Graduated.Task.C2C.core.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginDto loginDto) {
        try {
            System.out.println(loginDto);
            String accessToken = userService.login(loginDto.getId(), loginDto.getPassword());
            TokenDto tokenDto = new TokenDto(accessToken,"성공적으로 로그인 하였습니다.");
            Message<TokenDto> message = Message.of(200, tokenDto);
            return new ResponseEntity<>(message,HttpStatus.OK);
        } catch (Exception e) {
            ErrorMessage errorMessage = ErrorMessage.of(404, e.getMessage());
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String accessToken = jwtTokenUtil.resolveAccessToken(request);
        userService.logout(accessToken);
        Message<String> message = Message.of(200,"성공적으로 로그아웃 하였습니다.");
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PostMapping("/userInfo")
    public ResponseEntity<?> userInfo(HttpServletRequest request) {
        String accessToken = jwtTokenUtil.resolveAccessToken(request);
        try{
            userInfoDto userinfo = userService.userinfo(accessToken);
            Message<userInfoDto> message = Message.of(200,userinfo);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        catch (NullPointerException e){
            ErrorMessage errorMessage = ErrorMessage.of(404, e.getMessage());
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            ErrorMessage errorMessage = ErrorMessage.of(500, "다시 시도해주십시오");
            return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
