<%@ include file="taglibs.jsp" %>
<%@ include file="/scripts/optilploy.js"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:insertAttribute name="title"/></title>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="styles/main.css" media="screen" />
</head>
<body>


<div class="header">
	<h1><a href="#">Optiploy</a></h1>
	<h2>Optimal Deployment Tool</h2>
</div>

<div class="left">

	<tiles:insertAttribute name="navbar"/>

</div>

</div>

<div class="middle">

	<div id="messages" style="width:80%" align="center">

		<tiles:insertAttribute name="messages"/>

	</div>

		<tiles:insertAttribute name="content"/>

<br/><br/>

</div>

<div id="clear"></div>

</div>

	<div id="bottom"></div>

	</div>

</div>

<div id="footer">
	
</div>

</body>
</html>