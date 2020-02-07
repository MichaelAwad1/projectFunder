<#include "header.ftl">
	<div>
		<h3>profile of ${user.email}</h3>
		<label>User name: ${user.name}</label><br>
		<label>Number of created projects: ${createdCount}</label><br>
		<label>Number of supported projects: ${supportedCount}</lable><br>
	</div>
	
	
	<div class="row">
		<h1 style="text-align: left">Created projects</h1>
		<#list createdProjects as cp>
			<div class="column border">
				<img src=${cp.icon} width="150" height="150">
				<h2><a href="view_project?&amp;id=${cp.projectID}&amp;sessionEmail=${sessionEmail}">${cp.title}</a></h2>
				<div class="label">
					<label>Actual: ${cp.totalDonations}</label><br>
					<label>Status: ${cp.status}<label>
				</div>
			</div>
		</#list>
	</div>
	
	<div class="row">
		<h1 style="text-align: left">Supported projects</h1>
		<#list counter as c>
			<div class="column border">
				<img src=${supportedProjects[c].icon} width="150" height="150">
				<h2><a href="view_project?&amp;id=${supportedProjects[c].projectID}&amp;sessionEmail=${sessionEmail}">${supportedProjects[c].title}</a></h2>
				<div class="label">
					<label>Limit: ${supportedProjects[c].fundingLimit}</label><br>
					<label>Status: ${supportedProjects[c].status}</label><br>
					<label>Donated: ${supports[c].amount}</label>
					
				</div>
			</div>
		</#list>
	</div>
	
<#include "footer.ftl">