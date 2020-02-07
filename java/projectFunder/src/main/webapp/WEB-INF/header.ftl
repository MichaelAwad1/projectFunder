<!DOCTYPE HTML>
<html lang='de' dir='ltr'>
<head>
	<meta charset="utf-8" />
	<title>ProjectFunder - ${pagetitle}</title>
	<link type="text/css" href="css/style.css" rel="stylesheet" media="screen" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script>
	  	function search(ele) {
	  		// alert(ele.type);
	  	    if(event.key === 'Enter') {
	  	    	if(/\S/.test(ele.value)){
	  	    		document.location.href="/projectFunder/search?sessionEmail=${sessionEmail}&searchText=" + ele.value;
	  	    		 
	  	    	}else{
	  	  			alert("Spaces")  			
	  	    	}
	  	    }
	  	}
  	</script>
</head>
<body>
<div id="wrapper">
	<div id="logo">ProjectFunder</div>
    <div>
    	<ul id="navigation">
	    	<#assign x = pagetitle> 
	    	<#if x != "Login">
		    	<li><a class="button blue" href="view_main?&amp;sessionEmail=${sessionEmail}" title="ViewMain">View All Projects</a></li>
		    	<li><input class="button blue" type="text" placeHolder="Search with title" onkeydown="search(this)">
	    	</#if>
    	</ul>
    </div>
	<div id="content">