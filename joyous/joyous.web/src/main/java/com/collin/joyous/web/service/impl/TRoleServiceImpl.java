package com.collin.joyous.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collin.joyous.web.entity.TRole;
import com.collin.joyous.web.mapper.TRoleMapper;
import com.collin.joyous.web.service.ITRoleService;

@Service
@Transactional
public class TRoleServiceImpl implements ITRoleService {

	@Resource
	private TRoleMapper roleMapper;

	@Override
	public void save(TRole role) {
		roleMapper.insert(role);
	}
}
