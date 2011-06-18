<%@ include file="taglibs.jsp" %>

<security:authorize ifAllGranted="ROLE_USER">
	<h2>Navigation</h2>
</security:authorize>

<ul>

<security:authorize ifAllGranted="ROLE_USER">
	<li><a href="/userController.do?mode=profile">Edit Profile</a></li>	
</security:authorize>		
	
<security:authorize ifAllGranted="ROLE_ADMIN">
	<li><a href="javascript:toggle('User')">
		<img border='0' id='User_Icon'>User Actions</a>	
		<div id='User_Node' style=display?block:none >
			<a href="/lookupController.do?mode=user">User List</a><br>
			<a href="/userController.do?mode=add">Add User</a>			
		</div>
	</li>
	
	<li><a href="javascript:toggle('Role')">
		<img border='0' id='Role_Icon'>Role Actions</a>	
		<div id='Role_Node' style=display?block:none >
			<a href="/lookupController.do?mode=role">Role List</a><br>
			<a href="/roleController.do?mode=add">Add Role</a>			
		</div>
	</li>

	<li><a href="/reloadController.do?">Reload</a></li>
</security:authorize>

<security:authorize ifAllGranted="ROLE_USER">
	<li><a href="/about.html" title="About">About</a></li>
</security:authorize>

</ul>