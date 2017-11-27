package com.holdtime.userservice.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holdtime.framework.utils.common.ApiResponseModel;
import com.holdtime.framework.utils.common.ApiResponseModel.ApiResponseResult;
import com.holdtime.userservice.consumer.fegin.UserFeginClient;
import com.holdtime.userservice.model.TbUser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@RestController
public class UserController {
	@Autowired
	private UserFeginClient userFeginClient;
	@GetMapping("queryUserList")
	public ApiResponseModel<List<TbUser>> getUserlist(){
		return this.userFeginClient.queryUserList();
	}
	
	
}
