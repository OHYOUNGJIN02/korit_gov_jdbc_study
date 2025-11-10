package Study1.dao;

import Study1.entity.User;
import Study1.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO (Data Access Object)
 * 데이터베이스에 접근하고 데이터를 조작하는데 사용되는 객체
 * 일반적으로 데이터베이스에 대한 접근을 캡슐화
 * */
public class UserDao {
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    //addUser(User user)
    public int addUser(User user) {
        String sql =
                "insert into user_tb(user_id, username, password, age, create_dt) values (0, ?, ?, ?, now());";

        try (Connection con = ConnectionFactory.getConnection();
             //Statement.RETURN_GENERATED_KEYS
             //DB가 생성한 자동 증가 키를 되돌려받겠다 라는 옵션
             //단 실제로 키가 생성되려면 insert시 db에서 auto increment가 되도록 해야한다
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            //PreparedStatement
            //SQL문에 있는 ?(placeholder) 자리에 자바 값을 타입별로 안전하게 채운다
            //이 방식은 SQL 인젝션을 방지하고 , DB가 파라미터 타입을 안전하게 처리할 수 있게 돕는다
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());

            int updateInt = ps.executeUpdate(); //쿼리 실행: 변경된 행의 갯수를 반환

            try (ResultSet keys = ps.getGeneratedKeys()) {
                System.out.println(keys);
                if (keys.next()) {
                    int id = keys.getInt(1);
                    user.setUserId(id);
                }
            }
            return updateInt;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    //findUserByUsername(String username)
    public User findUserByUsername(String username) {
        String sql = "select user_id, username, password, age, create_dt from user_tb where username = ?";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);

            //조회 executeQuery()
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? toUser(rs) : null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //getUserAllList()
    public List<User> getUserAllList() {
        String sql = "select user_id, username, password, age, create_dt from user_tb";
        List<User> userList = new ArrayList<>();
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                userList.add(toUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public List<User> getUserListByKeyword(String keyword) {
        String sql = "select * from user_tb where username like ?";
        List<User> userList = new ArrayList<>();
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, "%" + keyword + "%");

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userList.add(toUser(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    private User toUser(ResultSet rs) throws SQLException {
        return User.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .age(rs.getInt("age"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}