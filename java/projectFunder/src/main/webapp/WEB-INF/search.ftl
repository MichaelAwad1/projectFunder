<#include "header.ftl">
	<div class="row">
		<h1 style="text-align: left">Search result</h1>
		<#if size == 0>
			<h1> No Results </h1>
		<#else>
			<#list searchedProjects as sp>
				<div class="column border">
					<img src=${sp.icon} width="150" height="150">
					<h2><a href="view_project?id=${sp.projectID}&amp;sessionEmail=${sessionEmail}">${sp.title}</a></h2>
					<div class="label">
						<label>From:   <a href="view_profile?email=${sp.creator}&amp;sessionEmail=${sessionEmail}">${sp.name}</a></label><br>
						<label>Actual: ${sp.totalDonations}<label>
					</div>
				</div>
			</#list>
		</#if>
	</div>
<#include "footer.ftl">