<#include "header.ftl">
	<form method="POST" style="font-size:16px;" action="new_project_fund?action=newDonation&amp;id=${id}&amp;sessionEmail=${sessionEmail}" title="view_project">
			<h1 style="color:#00458B">${project.title}</h1>
			
			<div style="padding-bottom: 7px">
				<label>amount of donation (in Euro): </label>
				<input type="number" required name="amount" min="0.1" max=${balance} step="0.00001">
		    </div>
	
			<div style="padding-bottom: 7px">
				<input type="checkbox" name="anonymous" value="true"> anonymous donation?<br>
		    </div>
		</fieldset>
		<button type="submit" id="submitDonation" class="button blue">Submit Donation</button>
	</form>
<#include "footer.ftl">