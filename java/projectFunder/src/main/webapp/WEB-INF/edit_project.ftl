<#include "header.ftl">

	<form method="POST" style="font-size: 15px;"action="edit_project?action=editProject&amp;id=${id}&amp;sessionEmail=${sessionEmail}" title="edit Project">
		<h1 class="borderBottom"style= "width: 21%; margin:auto; margin-bottom:5px">Edit project</h1>

		<div class="row left">
			<div class="col-30">
				<label>Title </label>
			</div>
			<div class="col-70">
				<input type="text" name="title" value="${project.title}" required maxlength="30">
	    	</div>
	    </div>
	    
	    <div class="row left">
			<div class="col-30">
				<label>Funding Limit (in Euro)</label>
			</div>
			<div class="col-70">
				<input type="number" name="fundingLimit" step="0.00001" value=${project.fundingLimit} min=${project.fundingLimit}>
	    	</div>
	    </div>
	    
	    
		<div class="row left">
			<div class="col-30">
				<label>Category: </label>
			</div>
			<div class="col-70">
				<#assign x = project.category>
				<#if x == 1>
					<input type="radio" name="category" value="1" checked required> Health & Wellness<br>
					<input type="radio" name="category" value="2"> Art & Creative Works<br>
					<input type="radio" name="category" value="3"> Education<br>
					<input type="radio" name="category" value="4"> Tech & Innovation<br>
	    		<#elseif x== 2>
	    			<input type="radio" name="category" value="1" required> Health & Wellness<br>
					<input type="radio" name="category" value="2" checked> Art & Creative Works<br>
					<input type="radio" name="category" value="3"> Education<br>
					<input type="radio" name="category" value="4"> Tech & Innovation<br>
				<#elseif x ==3>
					<input type="radio" name="category" value="1" required> Health & Wellness<br>
					<input type="radio" name="category" value="2"> Art & Creative Works<br>
					<input type="radio" name="category" value="3" checked> Education<br>
					<input type="radio" name="category" value="4"> Tech & Innovation<br>
				<#else>
					<input type="radio" name="category" value="1" required> Health & Wellness<br>
					<input type="radio" name="category" value="2"> Art & Creative Works<br>
					<input type="radio" name="category" value="3"> Education<br>
					<input type="radio" name="category" value="4" checked> Tech & Innovation<br>
				</#if>
	    	</div>
	    </div>
	    
	    <div class="row left">
			<div class="col-30">
				<label>parent project: </label>
			</div>
			<div class="col-70">
				<#assign y = project.pre>
				<#list createdProjects as cp>
					<#if y == cp.projectID && y != project.projectID>
						<input type="radio" name="parent" value="${cp.projectID}" checked>${cp.title}<br>
					<#else>
						<input type="radio" name="parent" value="${cp.projectID}">${cp.title}<br>
					</#if>
				</#list>
				<#if y == 0>
					<input type="radio" name="parent" value="0" checked required>No Parent	
	    		<#else>
	    			<input type="radio" name="parent" value="0" required>No Parent
	    		</#if>
	    	</div>
	    </div>
	    
	    <div class="row left">
			<div class="col-30">
				<label>description</label>
			</div>
			<div class="col-70">
				<textarea rows="5" cols="30" name="description">${project.description}</textarea>	
	    	</div>
	    </div>
		<button class="button blue" style="margin:auto; margin-bottom:5px" type="submit" id="updateProject">Update Project</button>
	</form>
<#include "footer.ftl">