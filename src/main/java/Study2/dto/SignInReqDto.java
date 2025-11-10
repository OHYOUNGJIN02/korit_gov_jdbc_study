package Study2.dto;

import Study2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInReqDto {
    private String username;
    private String password;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

    public boolean isEmpty() {

        return false;
    }

    public boolean isEmpty(String username) {
        return false;
    }
}
