<#list variableMap?keys as k>
    public ${variableMap[k].dataType} get${variableMap[k].columnHumpName}() {
			return ${k};
		}
		public void set${variableMap[k].columnHumpName}(${variableMap[k].dataType} ${k}) {
			this.${k} = ${k};
		}
</#list>
