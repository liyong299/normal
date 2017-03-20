package com.ly.dubbox.api;

import com.ly.dubbox.entities.User;

public interface UserService {
	User getUser(Long id);
}