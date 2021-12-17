package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

public interface UsersService {
    public Users addUsers(Users user);
    public Users updateUsers(Users user, int userId);
    public Users findUsersByUserId(int userId);
    public void deleteUsersByUserId(int userId);
    public Users findUsersByMobileNumber(String mobileNumber);
    public Users findUsersByUserName(String username);
    public ApiResponse<List<Users>>  findBloByConstituencyIdAndKeyword(Principal principal, String keyword);
    public ApiResponse<String> updateBloMobileNumber(Principal principal,Integer bloId, String mobileNumber);

    public ApiResponse<Users> getBloInformation(Principal principal);

}
