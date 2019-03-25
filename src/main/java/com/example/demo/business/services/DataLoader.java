package com.example.demo.business.services;

import com.example.demo.business.entities.Course;
import com.example.demo.business.entities.Role;
import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.CourseRepository;
import com.example.demo.business.entities.repositories.RoleRepository;
import com.example.demo.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole=roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "Pa$$word2019", "jim", "jimmerson",true,"jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com","Pa$$word2019","Admin","User",true,"admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        Course course = new Course("Astrophysics", "Neli D Tyson", "Just a course on stars", 3);
        course.setUser(user);
        courseRepository.save(course);

        course = new Course("Calculus", "Carol Henley", "Rate of change of rate of change", 3);
        course.setUser(user);
        courseRepository.save(course);

        course = new Course("Freshman English", "Geraldine Pegram", "Learn your language chilern", 3);
        course.setUser(user);
        courseRepository.save(course);
    }
}
