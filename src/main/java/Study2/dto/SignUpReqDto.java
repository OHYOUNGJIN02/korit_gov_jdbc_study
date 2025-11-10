package Study2.dto;

import Study2.entity.User;
import Study2.utill.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SignUpReqDto {
    private String username;
    private String password;
    private String email;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(PasswordEncoder.encode(password))
                .email(email)
                .build();
    }
}
