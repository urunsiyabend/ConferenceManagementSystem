package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.Paper;
import com.urunsiyabend.conferencemanagementsystem.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    HashMap<String, User> users = new HashMap<>();

    public UserRepository() {
        User user = User.builder()
                .email("admin@test.com")
                .password("admintest")
                .name("admin")
                .surname("admin")
                .role(User.Role.REVIEWER)
                .assignedPapers(new ArrayList<>())
                .maxNumberOfPapers(2)
                .build();

        users.put(user.getEmail(), user);
    }

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public void update(User user) {
        if (users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
        }
    }

    @Override
    public User findById(int id) {
        return users.get(id);
    }

    public User.Role findRoleByEmail(String email) {
        return users.get(email).getRole();
    }

    public void addAssignedPaper(User user, Paper paper) {
        user.getAssignedPapers().add(paper);
    }

    @Override
    public void deleteById(Long id) {
        return;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }

    @Override
    public List<User> findAll() { return List.copyOf(users.values());
    }

    @Override
    public List<User> findAllByRole(User.Role role) {
        List<User> Users = new ArrayList<>();
        for (User user: users.values()){
            if(user.getRole() == role){
                Users.add(user);
            }
        }

        return List.copyOf(Users);
    }
}
