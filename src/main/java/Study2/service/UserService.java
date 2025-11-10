package Study2.service;

import Study2.dao.UserDao;
import Study2.dto.SignInReqDto;
import Study2.dto.SignUpReqDto;
import Study2.entity.User;
import Study2.utill.PasswordEncoder;

import java.util.Optional;

public class UserService {
    private static UserService instance;
    private UserDao userDao;


    private UserService() {
        userDao = UserDao.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public int signup(SignUpReqDto signUpReqDto) {
        return userDao.addUser(signUpReqDto.toEntity());
    }

    public boolean isDuplicatedUsername(String username) {
        Optional<User> foundUser = userDao.findUserByUsername(username);
        return foundUser.isPresent();
    }

    public boolean isDuplicatedEmail(String email) {
       Optional<User> foundUser = userDao.findUserByEmail(email);
        return foundUser.isPresent();
    }

    public User signin(SignInReqDto signInReqDto) {
        Optional<User> foundUser = userDao.findUserByUsername(signInReqDto.getUsername());
        if (foundUser.isEmpty()) return null;
        User user = foundUser.get();
        if (PasswordEncoder.match(signInReqDto.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }
}
