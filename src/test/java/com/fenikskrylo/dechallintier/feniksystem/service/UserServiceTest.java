package com.fenikskrylo.dechallintier.feniksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UserServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

//    public User createUser(){
//        UserFormDto userFormDto = new UserFormDto();
//        userFormDto.setEmail("test@mail.com");
//        userFormDto.setName("테스트");
//        userFormDto.setPassword("1234");
//        return User.createUser(userFormDto, passwordEncoder);
//    }

//    @Test
//    @DisplayName("회원가입 기능")
//    public void saveUserTest(){
//        // given
//        User user = createUser();
//
//        // when
//        User savedUser = userService.saveUser(user);
//
//        // then
//        assertEquals(user.getEmail(), savedUser.getEmail());
//        assertEquals(user.getName(), savedUser.getName());
//        assertEquals(user.getPassword(), savedUser.getPassword());
//    }

//    @Test
//    @DisplayName("중복회원 확인 기능")
//    public void saveDuplicateUserTest(){
//        // given
//        User user1 = createUser();
//        User user2 = createUser();
//        userService.saveUser(user1);
//
//        // when
//        Throwable e = assertThrows(IllegalStateException.class, ()->{
//            userService.saveUser(user2);
//        });
//
//        // then
//        assertEquals("이미 계정이 존재합니다.", e.getMessage());
//    }
//
//    @Test
//    @DisplayName("API용 중복 EMAIL 체크")
//    public void apiEmailCheckTest(){
//        //given
//        User user = createUser();
//        String newEmail = "test2@mail.com";
//        String existingEmail = user.getEmail();
//
//        //when
//        boolean newResult = userService.emailCheck(newEmail);
//        boolean existingResult = userService.emailCheck("test@mail.com");
//        System.out.println(newResult);
//        System.out.println(existingResult);
//
//        //then
//        assertTrue(newResult);
//    }
}