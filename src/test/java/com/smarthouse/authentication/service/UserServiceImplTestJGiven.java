package com.smarthouse.authentication.service;


import com.smarthouse.authentication.repository.UserRepository;
import com.smarthouse.commonutil.entities.*;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeScenario;
import com.tngtech.jgiven.junit.SimpleScenarioTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class UserServiceImplTestJGiven extends SimpleScenarioTest<UserServiceImplTestJGiven.TestSteps> {

    private static UserRepository userRepository = Mockito.mock(UserRepository.class);

    @InjectMocks
    private static UserServiceImpl service = new UserServiceImpl(userRepository);

    @BeforeScenario
    public void init() {
        MockitoAnnotations.initMocks(this);
        // after initMocks() you can use the standard mockito functionality to mock methods and instances.
    }

    @Test
    public void find_all_test() {
        given().some_state();
        when().some_action();
        then().some_outcome();
    }

    @Test
    public void find_by_id_test() {
        given().some_state();
        when().some_action1();
        then().some_outcome1();
    }

    @Test
    public void delete_by_id_test() {
        given().some_state();
        when().some_action2();
        then().some_outcome2();
    }

    @Test
    public void delete_by_user_test() {
        given().some_state();
        when().some_action3();
        then().some_outcome3();
    }

    @Test
    public void save_user_test() {
        given().some_state();
        when().some_action4();
        then().some_outcome4();
    }
    public static class TestSteps extends Stage<TestSteps> {


        List<User> Users;

        List<User> op1;

        User user;

        User user1;

        User op;

        Long id;

        public TestSteps some_state() {
            Role role = new Role();
            role.setName("Ake");
            role.setAccessLevel(3);
            role.setId((long)1);
            user = new User();
            user.setId((long)1);
            user.setName("Ake");
            user.setEmail("133@mail.ua");
            user.setPassword("12345");
            user.setRole(role);
            return self();
        }



        public TestSteps some_action() {
            Users = new ArrayList<>();
            Users.add(user);
            op1 = new ArrayList<>();
            Mockito.when(userRepository.findAll()).thenReturn(Users);
            op1 = service.getAll();
            return self();
        }

        public TestSteps some_action1() {
            id = user.getId();
            Mockito.when(userRepository.findOne(id)).thenReturn(user);
            Optional<User> optionalUser = service.getById(id);
            user1 = optionalUser.isPresent() ? optionalUser.get() : new User();
            return self();
        }

        public TestSteps some_action2() {
            id = user.getId();
            service.deleteById(id);
            return self();
        }

        public TestSteps some_action3() {
            service.delete(user);
            return self();
        }

        public TestSteps some_action4() {
            Mockito.when(userRepository.saveAndFlush(user)).thenReturn(user);
            op = service.save(user);
            return self();
        }


        public void some_outcome() {
            Assert.assertEquals(op1, Users);
        }

        public void some_outcome1() {
            Assert.assertEquals(user, user1);
        }
        public void some_outcome2() {
            Mockito.verify(userRepository, times(1)).delete(id);
        }
        public void some_outcome3() {
            Mockito.verify(userRepository, times(1)).delete(user);
        }
        public void some_outcome4() {
            Assert.assertEquals(op, user);
    }

}
}