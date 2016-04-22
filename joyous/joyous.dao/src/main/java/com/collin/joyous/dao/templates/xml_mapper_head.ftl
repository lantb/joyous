<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper  
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
<mapper namespace="com.collin.joyous.web.mapper.${className}Mapper">  
<resultMap type="com.collin.joyous.web.entity.${className}" id="BaseResultMap">
	<#list variableMap?keys as k>
		<result column="${variableMap[k].columnName}"  jdbcType="${variableMap[k].dataTypeDB}" property="${k}" />
	</#list>
</resultMap>
<sql id="table_name"> ${tableName} </sql>
<sql id="Where_Clause">
	<where>
        <foreach collection="criteria" item="criterion">
          <choose>
            <when test="criterion.noValue">
              and ${r'${criterion.condition}'}
            </when>
            <when test="criterion.singleValue">
              and ${r'${criterion.condition}'} ${r'#{criterion.value}'}
            </when>
            <when test="criterion.betweenValue">
              and ${r'${criterion.condition}'} ${r'#{criterion.value}'} and ${r'#{criterion.secondValue}'}
            </when>
            <when test="criterion.listValue">
              and ${r'${criterion.condition}'}
              <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                ${r'#{listItem}'}
              </foreach>
            </when>
          </choose>
        </foreach>
    </where>
</sql>
<select id="selectByCriteria" parameterType="com.collin.joyous.web.entity.${className}Criteria" resultMap="BaseResultMap">
    SELECT
    <if test="distinct">
      DISTINCT
    </if>
    <include refid="Base_Column_List" />
    FROM <include refid="table_name" />
    <if test="criteria != null">
      <include refid="Where_Clause" />
    </if>
    <if test="orderByClause != null">
      order by ${r'${orderByClause}'}
    </if>
</select>
<select id="selectByPrimaryKey" parameterType="java.lang.String" resultMap="BaseResultMap">
    SELECT 
    <include refid="Base_Column_List" />
    FROM <include refid="table_name" />
    WHERE C_ID = ${r'#{cId,jdbcType=VARCHAR}'}
</select>
<delete id="deleteByPrimaryKey" parameterType="java.lang.String">
    DELETE FROM <include refid="table_name" />
    WHERE C_ID = ${r'#{cId,jdbcType=VARCHAR}'}
</delete>
<delete id="deleteByCriteria" parameterType="com.collin.joyous.web.entity.${className}Criteria">
    DELETE FROM <include refid="table_name" />
    <if test="criteria != null">
      <include refid="Where_Clause" />
    </if>
</delete>
<insert id="insert" parameterType="com.collin.joyous.web.entity.${className}">
	INSERT INTO <include refid="table_name" /> 
	(
	<include refid="Base_Column_List" />
	) values(
	<#assign keys = variableMap?keys>
	<#list keys as k>
		${r'#'}{${k},jdbcType=${variableMap[k].dataTypeDB}} <#if k_has_next>,</#if>
	</#list>
	)
</insert>
<select id="countByCriteria" parameterType="com.collin.joyous.web.entity.${className}Criteria" resultType="java.lang.Integer">
    SELECT COUNT(*) FROM <include refid="table_name" />
    <if test="criteria != null">
      <include refid="Where_Clause" />
    </if>
</select>
<update id="updateByPrimaryKeySelective" parameterType="com.collin.joyous.web.entity.${className}">
    update <include refid="table_name" />
    <set>
    <#list keys as k>
    	<if test="${k} != null">
        	${variableMap[k].columnName} = ${r'#{'}${k},jdbcType=${variableMap[k].dataTypeDB} ${r'}'},
      	</if>
	</#list>
    </set>
    where c_id = ${r'#{c_id,jdbcType=VARCHAR}'}
</update>
<update id="updateByPrimaryKey" parameterType="com.collin.joyous.web.entity.${className}">
    update <include refid="table_name" />
    set 
    <#assign keys = variableMap?keys>
	<#list keys as k>
		${variableMap[k].columnName} = ${r'#{'}${k},jdbcType=${variableMap[k].dataTypeDB} ${r'}'} <#if k_has_next>,</#if>
	</#list>
    where c_id = ${r'#{c_id,jdbcType=VARCHAR}'}
</update>


<sql id="Base_Column_List">
	<#assign keys = variableMap?keys>
	<#list keys as k>
		${variableMap[k].columnName} <#if k_has_next>,</#if>
	</#list>
</sql>

</mapper> 