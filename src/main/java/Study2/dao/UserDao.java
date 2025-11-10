package Study2.dao;

import Study2.entity.User;
import Study2.utill.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private static UserDao instance;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public int addUser(User user) {
        String sql = "INSERT INTO user_tb2 (user_id, username, password, email, create_dt) VALUES (0, ?, ?, ?, NOW())";
        try (Connection con = Study2.utill.ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());

            int result = ps.executeUpdate();

            if (result > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setUserid(rs.getInt(1));
                    }
                }
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Optional<User> findUserByUsername(String username){
        String sql = "select user_id, username, password, email, create_dt from user_tb2 where username = ?";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)

                ) {
                    ps.setString(1,username);

                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next() ? Optional.of(toUser(rs)) : Optional.empty();
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return Optional.empty();


        }
    }

    public static List<User> getUserListByKeyword(String keyword){
        String sql = " select * from user_tb2 where username like ?";
        List<User> userList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);


                ){
                ps.setString(1,"%" + keyword + "%" );
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()){
                        userList.add(toUser(rs));
                    }
                }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;

    }
    public Optional<User> findUserByEmail(String email) {
        String sql = "select * from user2_tb where email = ? ";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)

        ) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toUser(rs));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            return  Optional.empty();
        }
        return Optional.empty();
    }
    public static List<User> getUserAllList(){
        String sql = "select user_id, username, password, email, create_dt from user_tb2";
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
    private static User toUser(ResultSet rs) throws SQLException {
        return User.builder()
                .userid(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}


