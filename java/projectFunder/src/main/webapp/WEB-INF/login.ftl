<#include "header.ftl">
	<#list allUsers as au>
		<a href="view_main?&amp;sessionEmail=${au.email}" class="button blue">${au.email}</a>
	</#list>
<#include "footer.ftl">