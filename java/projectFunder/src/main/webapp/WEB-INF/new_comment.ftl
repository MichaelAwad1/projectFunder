<#include "header.ftl">
	<form method="POST" style="font-size:16px;" action="view_comment?action=newComment&amp;id=${id}&amp;sessionEmail=${sessionEmail}" title="submit">
		<h1 class="borderBottom"style="color:#00458B">${project.title}</h1>
		
		<div class="row" style="padding-bottom:7px">
				<textarea rows="5" cols="50" name ="comment" required placeholder="Write a comment"></textarea>
		</div>
		<div class="row" style="padding-bottom:7px">
				<input type="checkbox" name="anonymous" value="true"> anonymous comment?
    	</div>
		<input type="hidden" value="${project.creator}" name="projectCreator">
		<button type="submit" id="submitComment" class="button blue">Submit Comment</button>
	</form>
	<#include "footer.ftl">