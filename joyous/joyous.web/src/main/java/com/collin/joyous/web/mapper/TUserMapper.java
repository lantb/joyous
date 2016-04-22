package com.collin.joyous.web.mapper;

import com.collin.joyous.web.entity.TUser;
import com.collin.joyous.web.entity.TUserCriteria;
public interface TUserMapper extends BaseMapper<TUser,TUserCriteria>{

	TUser selectTUserByName(String username);
	
	TUser selectTUser(TUserCriteria criteria);
}
