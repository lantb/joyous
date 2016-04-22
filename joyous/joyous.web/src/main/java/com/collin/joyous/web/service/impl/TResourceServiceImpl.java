package com.collin.joyous.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collin.joyous.web.entity.TResource;
import com.collin.joyous.web.mapper.TResourceMapper;
import com.collin.joyous.web.service.ITResourceService;

@Service
@Transactional
public class TResourceServiceImpl implements ITResourceService {

	@Resource
	private TResourceMapper resourceMapper;

	@Override
	public List<TResource> selectTResource(TResource recource) {
		return resourceMapper.selectTResource(recource);
	}
}
