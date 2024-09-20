package Graduated.Task.C2C.User.Service;

import Graduated.Task.C2C.User.Entity.Users;
import Graduated.Task.C2C.User.Repository.UserRepository;
import Graduated.Task.C2C.core.JwtTokenUtil;
import Graduated.Task.C2C.core.RedisConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisConfig redisConfig;
    private Long expireTimeMs = 100*60*60L;
    @Transactional
    public String login(String email, String password) throws Exception {
        Users user = userRepository.findByUserId(email).orElseThrow(() -> new Exception("존재하지 않는 id입니다."));
        if (!Objects.equals(user.getPassword(), password)){
            throw new Exception("비밀번호가 틀렷습니다");
        }
        String Access_token = jwtTokenUtil.createToken(user.getId(),expireTimeMs);
        String reFresh_token = jwtTokenUtil.createReFreshToken(user.getId(),expireTimeMs);
        redisConfig.redisTemplate().opsForValue().set(user.getId(),reFresh_token, Duration.ofHours(3));
        return Access_token;
    }
}
