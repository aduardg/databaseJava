package ru.database.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.application.mappers.UserMapper;
import ru.database.application.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.RandomStringUtils;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public User getAutorization(User user){
        User checkUser = userMapper.getAutorization(user);
        if(checkUser!=null) {
            String token = generateToken();
            checkUser.setToken(token);
            checkUser.setPassword(null);
            UpdateUserToken(checkUser);
        }
        return checkUser;
    }

    public int UpdateUserToken(User user){
        return userMapper.setTokenUser(user);
    }



    private String generateToken(){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // coding error.
            System.out.println("codeing error. "+e);
            throw new RuntimeException(e);
        }

        StringBuffer hexString = new StringBuffer();
        byte[] data = md.digest(RandomStringUtils.randomAlphabetic(10).getBytes());
        for (int i=0; i<data.length; i++){
            hexString.append(Integer.toHexString( (data[i]>> 4) & 0x0F ) );
            hexString.append(Integer.toHexString( data[i] & 0x0F ) );
        }
        return hexString.toString();
    }



}
