package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.repository.UsersRepository;
import com.electioncomission.ec.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users addUsers(Users users) {
        return this.usersRepository.save(users);
    }

    @Override
    public Users updateUsers(Users users, int userId) {
        if (this.usersRepository.findUsersByUserId(userId) != null)
            return this.usersRepository.save(users);
        return null;
    }

    @Override
    public Users findUsersByUserId(int userId) {
        return this.usersRepository.findUsersByUserId(userId);
    }

    @Override
    public void deleteUsersByUserId(int userId) {
        this.usersRepository.deleteUsersByUserId(userId);
    }

    @Override
    public Users findUsersByMobileNumber(String mobileNumber) {
        return this.usersRepository.findUsersByMobileNumber(mobileNumber);
    }
}
