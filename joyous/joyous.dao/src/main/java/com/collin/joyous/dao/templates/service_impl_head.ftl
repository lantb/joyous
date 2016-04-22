<#include "/class_01_package.ftl" >
<#include "/class_02_import.ftl" >
<#include "/class_03_head.ftl" >
	@Resource
	private ${entityName}Mapper mapper;
	
	public BaseMapper<${entityName}, ${entityName}Criteria> getMapper(){
		return mapper;
	}
<#include "/class_06_tail.ftl" >

