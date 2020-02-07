<#include "header.ftl">
	<form method="POST" action="new_project?action=newProject&amp;sessionEmail=${sessionEmail}">
		<h1 class="borderBottom myProfile" style= "width:17%;margin: auto; margin-bottom:5px">  New project</h1>	
		<div class="row left">
			<div class="col-30">
				<label>Title </label>
			</div>
			<div class="col-70">
				<input type="text" name="title" required maxlength="30">
	    	</div>
	    </div>
	    
	    <div class="row left">
			<div class="col-30">
				<label>Funding Limit (in Euro)</label>
			</div>
			<div class="col-70">
				<input type="number" name="fundingLimit" required min="100" title="100 is the minimum" step="0.00001">
	    	</div>
	    </div>
	    
	    
		<div class="row left">
			<div class="col-30">
				<label>Category: </label>
			</div>
			<div class="col-70">
				<input type="radio" name="category" value="Health & Wellness" required> Health & Wellness<br>
				<input type="radio" name="category" value="Art & Creative Works"> Art & Creative Works<br>
				<input type="radio" name="category" value="Education"> Education<br>
				<input type="radio" name="category" value="Tech & Innovation"> Tech & Innovation<br>
	    	</div>
	    </div>
	    
	    <div class="row left">
			<div class="col-30">
				<label>parent project: </label>
			</div>
			<div class="col-70">
				<#list createdProjects as cp>
					<input type="radio" name="parent" value="${cp.projectID}">${cp.title}<br>
				</#list>
				<input type="radio" name="parent" value="0" required>No Parent	
	    	</div>
	    </div>
	    
	    <div class="row left">
			<div class="col-30">
				<label>description</label>
			</div>
			<div class="col-70">
				<textarea rows="5" cols="30" name="description"></textarea>	
	    	</div>
	    </div>
		<button href="NewProject" type="submit" id="submitProject" class="button blue" style="margin-bottom:5px">Create</button>
	</form>
<#include "footer.ftl">