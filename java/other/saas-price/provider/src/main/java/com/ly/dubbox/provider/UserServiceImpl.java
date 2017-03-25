/**
 * 
 */
package com.ly.dubbox.provider;

/**
 * @author hugoyang
 *
 */
import com.ly.dubbox.api.UserService;
import com.ly.dubbox.entities.User;
 
public class UserServiceImpl implements UserService {
 
    public User getUser(Long id) {
        return new User(id, "username" + id);
    }
}