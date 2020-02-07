<#include "header.ftl">
<div>
	<h1>Information</h1>
	<img src=${project.icon} height="150" width="150">
	<h1>${project.title}</h1>
	<h3>from <a href="view_profile?email=${project.creator}&amp;sessionEmail=${sessionEmail}">${creatorName}</a></h2>
	<p>${project.description}</p>
	
	<div class="borderBottom left">
		<label>Finance limit: ${project.fundingLimit} Euro</label><br>
		<label>Total donations:</label><label>${project.totalDonations}</label><br>
		<label>Status: ${project.status}</label><br>
		<#if project.pre != 0>
			<label>Parent project: ${parentProject.title}</label><br>
		<#else>
			<label>Parent project: No parent</label><br>
		</#if>
	</div>
	<div class="borderBottom">
		<h2 class="left">Action lists</h2>
		<#if related == "true">
			<a class="button green btn-disabled" disabled="disabled" href="new_project_fund?id=${id}&amp;sessionEmail=${sessionEmail}">Donate</a>
			<a class="button red" href="view_project?action=deleteProject&amp;id=${id}&amp;sessionEmail=${sessionEmail}">Delete</a>
	 		<#if project.status == "opened">
	 			<a class="button blue" href="edit_project?id=${id}&amp;sessionEmail=${sessionEmail}">Edit project</a>
	 		<#else>
	 			<a class="button blue btn-disabled" disabled="disabled" href="edit_project?id=${id}&amp;sessionEmail=${sessionEmail}">Edit project</a>
	 		</#if>
		<#else>
			<#if project.status == "opened" && (donated =="false")>
				<a class="button green" href="new_project_fund?id=${id}&amp;sessionEmail=${sessionEmail}">Donate</a>			
			<#else>
				<a class="button green btn-disabled" disabled="disabled" href="new_project_fund?id=${id}&amp;sessionEmail=${sessionEmail}">Donate</a>
			</#if>
			<a class="button red btn-disabled" disabled="disabled" href="view_project?action=deleteProject&amp;id=${id}&amp;sessionEmail=${sessionEmail}">Delete</a>
	 		<a class="button blue btn-disabled" disabled="disabled" href="edit_project?id=${id}&amp;sessionEmail=${sessionEmail}">Edit project</a>
	 	</#if>
	 	
	</div>
	<div class="borderBottom left">
		<h2>List of donators</h2>
		<#list donators as d>
			<#assign x = d.donationStatus>
			<#if x =="public">
				<label>${d.name}: ${d.amount} Euro</label><br>
			<#else>
				<label>Anonymous: ${d.amount} Euro</label><br>
			</#if>
		</#list>
	</div>
	<div class="borderBottom left">
		<h2>List of comments</h2>
		<#list comments as c>
			<#assign y = c.commentStatus>
			<#if y =="public">
				<label>${c.userName}: ${c.text}</label><br>
				<#else>
					<label>Anonymous: ${c.text}</label><br>
				</#if>
		</#list>
	</div>
	<#if related != "true">
		<#if project.status == "opened">
			<a class="button blue" href="view_comment?&amp;id=${id}&amp;sessionEmail=${sessionEmail}">Comment</a>
		<#else>
		<a class="button blue btn-disabled" disabled="disabled" href="view_comment?&amp;id=${id}&amp;sessionEmail=${sessionEmail}">Comment</a>
		</#if>
	<#else>
		<a class="button blue btn-disabled" disabled="disabled" href="view_comment?&amp;id=${id}&amp;sessionEmail=${sessionEmail}">Comment</a>
	</#if>
</div>
<#include "footer.ftl">