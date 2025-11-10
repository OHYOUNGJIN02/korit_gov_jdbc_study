package Todo.dao;

import Todo.entity.User;
import Study1.util.ConnectionFactory;
import Todo.entity.User;

import java.sql.*;

public class TodoDao {
    private static TodoDao instance;
    public TodoDao() {}




    public static TodoDao getInstance() {
        if (instance == null) {
            instance = new TodoDao();
        }
        return instance;


    }

    private User toTodo(ResultSet rs) throws SQLException {
        return User.builder()
                .todoId(rs.getInt("todo_id"))
                .content(rs.getString("content"))
                .username(rs.getString("username"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }


    public int addUser(User user){
        String sql = "insert into todo_tb(todo_id, content, username, create_dt) values (0, ?, ?, ?, now())";
        try (Connection con = ConnectionFactory.getConnection();
             //Statement.RETURN_GENERATED_KEYS
             //DB가 생성한 자동 증가 키를 되돌려 받겠다 라는 옵션.
             //단 실제로 키가 생성되려면 insert시 db에서 auto increment가 되도록 해야한다.

             //PreparedStatement
             //SQL문에 있는 ?(placeholder) 자리에 자바 값을 타입 별로 안전하게 채운다
             //이 방식은 SQL 인젝션을 방지하고 DB가 파라미터 타입을 안전하게 처리할 수 있게 돕는다
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getContent());
            ps.setString(2,user.getUsername());

            int updateInt = ps.executeUpdate(); //쿼리 실행 : 변경된 행의 갯수를 반환
            try (ResultSet keys = ps.getGeneratedKeys()){
                System.out.println(keys.toString());
                if (keys.next()){
                    int id = keys.getInt(1);
                    user.setTodoId(id);
                }
            }
            return updateInt;
            }catch(SQLException e){
                e.printStackTrace();
                return 0;

    }}
    public User findTodoByUsername(String username) {
        String sql = "select todo_id, content, username, create_dt from todo_tb where username = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? toTodo(rs) : null; // ✅ 수정된 부분
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}