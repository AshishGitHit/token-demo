package com.example.jwt.token.demo.service;

import com.example.jwt.token.demo.entity.MyRole;
import com.example.jwt.token.demo.entity.MyUser;
import com.example.jwt.token.demo.repo.RoleRepo;
import com.example.jwt.token.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUser registerNewUser(MyUser myUser) {

        MyRole myRole = roleRepo.findById("User").get();

        Set<MyRole> roles = new HashSet<>();
        roles.add(myRole);
        myUser.setMyRole(roles);

        myUser.setUserPassword(getEncodedPassword(myUser.getUserPassword()));
        return userRepo.save(myUser);
    }

    public void initRolesAndUser() {
        MyRole adminRole = new MyRole();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("This is admin");
        roleRepo.save(adminRole);

        MyRole userRole = new MyRole();
        userRole.setRoleName("User");
        userRole.setRoleDescription("This is user");
        roleRepo.save(userRole);

        MyUser adminUser = new MyUser();
        adminUser.setUserName("admin123");
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastNmae("admin");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        Set<MyRole> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setMyRole(adminRoles);
        userRepo.save(adminUser);

//        MyUser user = new MyUser();
//        user.setUserName("ashish123");
//        user.setUserFirstName("ashish");
//        user.setUserLastNmae("ranjan");
//        user.setUserPassword(getEncodedPassword("ashish@pass"));
//        Set<MyRole> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setMyRole(userRoles);
//        userRepo.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
