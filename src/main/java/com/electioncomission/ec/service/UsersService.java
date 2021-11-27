package com.electioncomission.ec.service;

import com.electioncomission.ec.entity.Users;

public interface UsersService {
    public Users addUsers(Users user);
    public Users updateUsers(Users user, int userId);
    public Users findUsersByUserId(int userId);
    public void deleteUsersByUserId(int userId);
}
