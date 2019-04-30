package com.smarthouse.authentication.service

import com.smarthouse.authentication.repository.UserRepository
import com.smarthouse.commonutil.entities.Role
import com.smarthouse.commonutil.entities.User


class UserServiceImplTestSpock extends spock.lang.Specification {

    def userRepository = Mock(UserRepository)

    def  service = UserServiceImpl


    def"get all users"(){
        given:
        service = new UserServiceImpl(userRepository);
        Role role = new Role();
        role.setName("Ake");
        role.setAccessLevel(3);
        role.setId((long)1);
        User user = new User();
        user.setId((long)1);
        user.setName("Ake");
        user.setEmail("133@mail.ua");
        user.setPassword("12345");
        user.setRole(role);
        Long id = user.getId();
        List<User> users = new ArrayList<User>();
        users.add(user);
        when:
        List<User> op1 =  service.getAll();
        then:
        1*userRepository.findAll()
    }
    def "test Find By ID"(){
        given:
        service = new UserServiceImpl(userRepository);
        Role role = new Role();
        role.setName("Ake");
        role.setAccessLevel(3);
        role.setId((long)1);
        User user = new User();
        user.setId((long)1);
        user.setName("Ake");
        user.setEmail("133@mail.ua");
        user.setPassword("12345");
        user.setRole(role);
        Long id = user.getId();
        when:
        Optional<User> optionalUser = service.getById(id);
        User user1 = optionalUser.isPresent() ? optionalUser.get() : new User();
        then:
        1*userRepository.findOne(id)

    }
    def "test Delete By Id"(){
        given:
        service = new UserServiceImpl(userRepository);
        Role role = new Role();
        role.setName("Ake");
        role.setAccessLevel(3);
        role.setId((long)1);
        User user = new User();
        user.setId((long)1);
        user.setName("Ake");
        user.setEmail("133@mail.ua");
        user.setPassword("12345");
        user.setRole(role);
        Long id = user.getId();
        when:
        service.deleteById(id)
        then:
        1* userRepository.delete(id)

    }

    def "test Save"(){
        given:
        service = new UserServiceImpl(userRepository);
        Role role = new Role();
        role.setName("Ake");
        role.setAccessLevel(3);
        User user = new User();
        user.setId((long)1);
        user.setName("Ake");
        user.setEmail("133@mail.ua");
        user.setPassword("12345");
        user.setRole(role);
        when:
        User op = service.save(user);
        then:
        1* userRepository.saveAndFlush(user)

    }

    def "test Delete User"(){
        given:
        service = new UserServiceImpl(userRepository);
        Role role = new Role();
        role.setId((long)4);
        role.setName("Ake");
        role.setAccessLevel(3);
        User user = new User();
        user.setId((long)1);
        user.setName("Ake");
        user.setEmail("133@mail.ua");
        user.setPassword("12345");
        user.setRole(role);
        when:
        service.delete(user);
        then:
        1 * userRepository.delete(user);

    }
}
