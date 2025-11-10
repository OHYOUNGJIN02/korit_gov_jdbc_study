package Study2.entity;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor

public class User {
    private Integer userid;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createDt;

    }
