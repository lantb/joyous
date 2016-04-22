package com.collin.joyous.web.mapper;

import java.util.List;

import com.collin.joyous.web.entity.TResource;
import com.collin.joyous.web.entity.TResourceCriteria;
public interface TResourceMapper extends BaseMapper<TResource,TResourceCriteria>{

	List<TResource> selectTResource(TResource recource);
}
