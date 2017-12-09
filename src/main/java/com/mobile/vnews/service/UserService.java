package com.mobile.vnews.service;


import com.mobile.vnews.mapper.UserMapper;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.User;
import com.mobile.vnews.util.IdUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Create by xuantang
 * @date on 12/9/17
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    /**
     * 日志输出
     */
    private static Logger log = LogManager.getLogger(UserService.class);

    public BasicResponse<String> register(User user) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "success";
        try {
            // add user
            user.setID(IdUtils.getUUID());
            System.out.println(user.toString());
            int res = userMapper.addUser(user);
            log.info(res);
        } catch (Exception e) {
            code = 400;
            message = e.getMessage();
        }
        // set values
        response.setCode(code);
        response.setMessage(message);
        // log
        log.info(response.toString());
        return response;
    }
}