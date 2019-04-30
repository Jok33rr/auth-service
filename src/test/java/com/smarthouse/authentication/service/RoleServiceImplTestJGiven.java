package com.smarthouse.authentication.service;


import com.smarthouse.authentication.repository.RoleRepository;
import com.smarthouse.commonutil.entities.Device;
import com.smarthouse.commonutil.entities.Role;
import com.smarthouse.commonutil.entities.UserDevice;
import com.smarthouse.commonutil.entities.UserDeviceId;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeScenario;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit.ScenarioTest;
import com.tngtech.jgiven.junit.SimpleScenarioTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RoleServiceImplTestJGiven extends SimpleScenarioTest<RoleServiceImplTestJGiven.TestSteps> {

    private static RoleRepository roleRepository = Mockito.mock(RoleRepository.class);

    @InjectMocks
    private static RoleServiceImpl service = new RoleServiceImpl(roleRepository);

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
    public void delete_by_role_test() {
        given().some_state();
        when().some_action3();
        then().some_outcome3();
    }

    @Test
    public void save_role_test() {
        given().some_state();
        when().some_action4();
        then().some_outcome4();
    }
    public static class TestSteps extends Stage<TestSteps> {


        List<Role> Roles;

        List<Role> op1;

        Role role = new Role();

        Role role1;

        Role op;

        Long id;

        public TestSteps some_state() {
            role.setName("Ake");
            role.setAccessLevel(3);
            role.setId((long)1);
            roleRepository.save(role);
            Roles = new ArrayList<>();
            Roles.add(role);
            return self();
        }



        public TestSteps some_action() {
            op1 = new ArrayList<>();
            Mockito.when(roleRepository.findAll()).thenReturn(Roles);
            op1 = service.getAll();
            return self();
        }

        public TestSteps some_action1() {
            id = role.getId();
            Mockito.when(roleRepository.findOne(id)).thenReturn(role);
            Optional<Role> optionalUser = service.getById(id);
            role1 = optionalUser.isPresent() ? optionalUser.get() : new Role();
            return self();
        }

        public TestSteps some_action2() {
            id = role.getId();
            service.deleteById(id);
            return self();
        }

        public TestSteps some_action3() {
            service.delete(role);
            return self();
        }

        public TestSteps some_action4() {
            Mockito.when(roleRepository.saveAndFlush(role)).thenReturn(role);
            op = service.save(role);
            return self();
        }

        public void some_outcome() {
            Assert.assertEquals(op1, Roles);
        }
        public void some_outcome1() {
            Assert.assertEquals(role, role1);
        }
        public void some_outcome2() {
            Mockito.verify(roleRepository, times(1)).delete(id);
        }
        public void some_outcome3() {
            Mockito.verify(roleRepository, times(1)).delete(role);
        }
        public void some_outcome4() {
            Assert.assertEquals(op, role);
        }
    }

}
