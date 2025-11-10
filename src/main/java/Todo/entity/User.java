package Todo.entity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder


public class User {
    private Integer todoId;
    private String content;
    private String username;
    private LocalDateTime createDt;
}

