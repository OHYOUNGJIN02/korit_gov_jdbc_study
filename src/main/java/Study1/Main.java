package Study1;

import Study1.dao.UserDao;
import Study1.entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();

        //insert
//        User user = User.builder()
//                .username("dongyoon7212")
//                .password("1q2w3e4r")
//                .age(27)
//                .build();
//        int count = userDao.addUser(user);
//        System.out.println("추가된 행 개수: " + count);
//        System.out.println("추가된 유저 정보: " + user);

        //단건조회
        User foundUser = userDao.findUserByUsername("trg9956");
        System.out.println("foundUser = " + foundUser);

        //전체조회
        List<User> userList = userDao.getUserAllList();
        userList.forEach(System.out::println);

        //username 키워드 검색
        List<User> userList1 = userDao.getUserListByKeyword("trg");
        userList1.forEach(System.out::println);
    }
}
