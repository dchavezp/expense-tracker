package com.devvitt.expensetrackapi.services;

import com.devvitt.expensetrackapi.domain.User;
import com.devvitt.expensetrackapi.exceptions.EtAuthException;
import com.devvitt.expensetrackapi.repositories.UserRepository;
import com.devvitt.expensetrackapi.services.interfaces.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService implements UserServiceI {
    @Autowired
    UserRepository userRepository;
    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if(email!=null) email=email.toLowerCase();
        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public User registerUser(User user) throws EtAuthException {
        Pattern pattern=Pattern.compile("^(.+)@(.+)$");
        if(user.getEmail()!=null) user.setEmail((user.getEmail().toLowerCase()));
        if(!pattern.matcher(user.getEmail()).matches()) throw new EtAuthException("Invalid Email format");
        Integer count = userRepository.getCountByEmail(user.getEmail());
        if(count > 0) throw new EtAuthException("Email already in use");
        Integer userId=userRepository.create(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());
        return userRepository.findById(userId);
    }

    @Override
    public List<User> listOfUsers() {
        return userRepository.getListOfUsers();
    }


}
