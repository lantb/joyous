package com.collin.joyous.web.service;

import java.util.List;

import com.collin.joyous.web.entity.TResource;


public interface ITResourceService {

	
	List<TResource> selectTResource(TResource resource);
}
