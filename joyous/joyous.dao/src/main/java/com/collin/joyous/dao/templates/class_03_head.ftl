<#if annotation!=''>@${annotation}</#if>
public class ${className} <#if superClassName!=''>extends ${superClassName} </#if><#if (implementNameList?size >0)> implements <#list implementNameList as implementName>${implementName}<#if implementName_has_next>,</#if></#list></#if>{
