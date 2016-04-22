package com.collin.joyous.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.collin.joyous.web.entity.TUser;
import com.collin.joyous.web.entity.TUserCriteria;
import com.collin.joyous.web.mapper.BaseMapper;
import com.collin.joyous.web.mapper.TUserMapper;
import com.collin.joyous.web.service.ITUserService;

@Service
public class TUserServiceImpl extends BaseService<TUser, TUserCriteria> implements ITUserService {
	
	@Resource
	private TUserMapper userMapper;
	
	public BaseMapper<TUser, TUserCriteria> getMapper(){
		return userMapper;
	}
}
