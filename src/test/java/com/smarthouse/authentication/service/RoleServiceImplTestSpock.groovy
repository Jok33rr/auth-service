package com.smarthouse.authentication.service

import com.smarthouse.authentication.repository.RoleRepository
import com.smarthouse.commonutil.entities.Role

class RoleServiceImplTestSpock extends spock.lang.Specification {


    def roleRepository = Mock(RoleRepository)

    def  service = RoleServiceImpl


    def"get all roles"(){
        given:
        service = new RoleServiceImpl(roleRepository);
        Role role = new Role();
        role.setName("Ake");
        role.setAccessLevel(3);
        role.setId((long)1);
        roleRepository.save(role);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        when:
        List<Role> op1 =  service.getAll();
        then:
        1*roleRepository.findAll()
    }
    def "test Find By ID"(){
        given:
        service = new RoleServiceImpl(roleRepository);
        Role role = new Role();
        role.setName("Ake");
        role.setAccessLevel(3);
        role.setId((long)1);
        Long id = role.getId();
        when:
        Optional<Role> optionalUser = service.getById(id);
        Role role1 = optionalUser.isPresent() ? optionalUser.get() : new Role();
        then:
        1*roleRepository.findOne(id)

    }
    def "test Delete By Id"(){
        given:
        service = new RoleServiceImpl(roleRepository);
        Role role = new Role();
        role.setName("Ake");
        role.setAccessLevel(3);
        role.setId((long)1);
        roleRepository.save(role);
        Long id = role.getId();
        when:
        service.deleteById(id)
        then:
        1* roleRepository.delete(id)

    }

    def "test Save"(){
        given:
        service = new RoleServiceImpl(roleRepository);
        Role role = new Role();
        role.setName("Ake");
        role.setAccessLevel(3);
        when:
        Role op = service.save(role);
        then:
        1* roleRepository.saveAndFlush(role)

    }

    def "test Delete Role"(){
        given:
        service = new RoleServiceImpl(roleRepository);
        Role role = new Role();
        role.setId((long)4);
        role.setName("Ake");
        role.setAccessLevel(3);
        when:
        service.delete(role);
        then:
        1 * roleRepository.delete(role);

    }
}