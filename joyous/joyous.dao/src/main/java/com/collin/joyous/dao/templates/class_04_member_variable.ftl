<#list variableMap?keys as k>
    private ${variableMap[k].dataType} ${k};	<#if (variableMap[k].comments != '')>/* ${variableMap[k].comments} */</#if>
</#list>

