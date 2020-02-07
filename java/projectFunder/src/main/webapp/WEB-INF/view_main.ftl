<#include "header.ftl">

		<a href="view_profile?action=myProfile&amp;sessionEmail=${sessionEmail}" class="button blue"style= "float: left; width: 30%; margin-right: 70%;">My Profile</a>	
		<div class="row">
			<h1 style="text-align: left">Open projects </h1>
			<#list openProjects as op>
				<div class="column border">
					<img src=${op.icon} width="150" height="150">
					<h2><a href="view_project?id=${op.projectID}&amp;sessionEmail=${sessionEmail}">${op.title}</a></h2>
					<div class="label">
						<label>From:   <a href="view_profile?email=${op.creator}&amp;sessionEmail=${sessionEmail}">${op.name}</a></label><br>
						<label>Actual: ${op.totalDonations}<label>
					</div>
				</div>
			</#list>
		</div>
		<div class="row">
			<h1 style="text-align: left">Closed projects</h1>
			<#list closedProjects as cp>
				<div class="column border">
					<img src=${cp.icon} width="150" height="150">
					<h2><a href="view_project?id=${cp.projectID}&amp;sessionEmail=${sessionEmail}">${cp.title}</a></h2>
					<div class="label">
						<label>From:   <a href="view_profile?email=${cp.creator}&amp;sessionEmail=${sessionEmail}">${cp.name}</a></label><br>
						<label>Actual: ${cp.totalDonations}<label>
					</div>
				</div>
			</#list>
		</div>
		<a href="new_project?sessionEmail=${sessionEmail}" class="button blue" style="margin-botton: 50px">New Project</a>
<#include "footer.ftl">