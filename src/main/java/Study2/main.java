package Study2;

import Study2.dao.UserDao;
import Study2.dto.SignInReqDto;
import Study2.dto.SignUpReqDto;
import Study2.entity.User;
import Study2.service.UserService;
import Study2.utill.PasswordEncoder;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = UserService.getInstance();
        User principal = null;


        while (true) {
            System.out.println("[회원관리]");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 전체회원 조회");
            System.out.println("4. 회원 검색");
            System.out.println("q. 종료");
            String selectMenu = scanner.nextLine();

            if ("q".equalsIgnoreCase(selectMenu)) {
                System.out.println("프로그램 종료");

                break;
            } else if ("1".equals(selectMenu)) {
                //todo : 회원가입 메소드 호출
                System.out.println("[회원가입]");
                SignUpReqDto signUpReqDto = new SignUpReqDto();
                while (true) {
                    System.out.print("username >> ");
                    signUpReqDto.setUsername(scanner.nextLine());
                    if (!userService.isDuplicatedUsername(signUpReqDto.getUsername())) {
                        break;
                    }
                    System.out.println("사용할 수 없는 아이디 입니다.");
                }
                System.out.print("password >> ");
                signUpReqDto.setPassword(scanner.nextLine());

                while(true) {
                    System.out.print("email >> ");
                    signUpReqDto.setEmail(scanner.nextLine());
                    if (!userService.isDuplicatedEmail(signUpReqDto.getEmail())) {
                        break;
                    }
                    System.out.println("사용 불가능한 이메일");
                }

                int result = userService.signup(signUpReqDto);
                if (result == 1) {
                    System.out.println("[ 회원가입 성공 ]");
                } else {
                    System.out.println("회원가입 실패");
                }


            } else if ("2".equals(selectMenu)) {
                System.out.println("[로그인]");
                System.out.print("username >> ");
                String username = scanner.nextLine();
                System.out.print("password >> ");
                String password = scanner.nextLine();
                SignInReqDto signInReqDto = new SignInReqDto(username, password);
                principal = userService.signin(signInReqDto);
                if (principal == null) {
                    System.out.println("로그인 실패");
                } else {
                    System.out.println("로그인 성공");
                }

            }else if ("3".equals(selectMenu)) {
                System.out.println("[전체 회원 조회]");

                List<User> userList = UserDao.getUserAllList();
                userList.forEach(System.out::println);

                //todo : 전체 회원 조회 메소드 호출

            } else if ("4".equals(selectMenu)) {
                System.out.println("[회원 검색]");
                List<User> userList1 = UserDao.getUserListByKeyword("잘도이");
                userList1.forEach(System.out::println);
                //todo : 회원 검색 메소드 호출


            }}

        }
        }

