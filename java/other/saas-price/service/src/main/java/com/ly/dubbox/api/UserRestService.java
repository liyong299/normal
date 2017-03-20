package com.ly.dubbox.api;

import javax.validation.constraints.Min;

import com.ly.dubbox.entities.User;

public interface UserRestService {
	User getUser(@Min(value = 1L, message = "User ID must be greater than 1") Long id);
}