package com.example.mschedule.service.impl;

import com.example.mschedule.entity.Task;
import com.example.mschedule.entity.User;
import com.example.mschedule.repository.UserRepository;
import com.example.mschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAllWithMemberAccess();
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElse(null);
    }

    public Boolean getFreeStatus(User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH);
        List<Task> listOfTasks = user.getTasks();
        Date currentTime = new Date();

        for (Task task : listOfTasks) {
            try {
                Date taskStartTime = dateFormat.parse(task.getStartTime());
                Date taskEndTime = dateFormat.parse(task.getEndTime());
                if (currentTime.after(taskStartTime) && currentTime.before(taskEndTime)) {
                    return false;
                } else {
                    return true;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return true;

    }


}
