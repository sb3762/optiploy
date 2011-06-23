<%@ include file="taglibs.jsp" %>

<security:authorize ifAllGranted="ROLE_USER">
	<h2>Navigation</h2>
</security:authorize>

<ul>

<security:authorize ifAllGranted="ROLE_USER">
	<li><a href="/home.html" title="Home">Home</a></li>
	<li><a href="/userController.do?mode=profile">Edit Profile</a></li>	
</security:authorize>		
	
<security:authorize ifAllGranted="ROLE_ADMIN">
	<li><a href="javascript:toggle('User')">User Actions</a>	
		<div id='User_Node' style=display?block:none>
			<a href="/lookupController.do?mode=user">User List</a><br>
			<a href="/userController.do?mode=add">Add User</a>			
		</div>
	</li>
	
	<li><a href="javascript:toggle('Job')">Job Actions</a>
		<div id='Job_Node' style=display?block:none>
			<a href="/lookupController.do?mode=job">Job List</a><br>
			<a href="/jobController.do?mode=add">Add Job</a>			
		</div>
	</li>
	
	<li><a href="javascript:toggle('Role')">Role Actions</a>
		<div id='Role_Node' style=display?block:none>
			<a href="/lookupController.do?mode=role">Role List</a><br>
			<a href="/roleController.do?mode=add">Add Role</a>			
		</div>
	</li>
	
	<li><a href="javascript:toggle('Environment')">Environment Actions</a>
		<div id='Environment_Node' style=display?block:none>
			<a href="/lookupController.do?mode=environment">Environment List</a><br>
			<a href="/environmentController.do?mode=add">Add Environment</a>			
		</div>
	</li>
	
	<li><a href="javascript:toggle('Function')">Function Actions</a>
		<div id='Function_Node' style=display?block:none>
			<a href="/lookupController.do?mode=function">Function List</a><br>
			<a href="/functionController.do?mode=add">Add Function</a>			
		</div>
	</li>
	
	<li><a href="javascript:toggle('Release')">Release Actions</a>
		<div id='Release_Node' style=display?block:none>
			<a href="/lookupController.do?mode=release">Release List</a><br>
			<a href="/releaseController.do?mode=add">Add Release</a>			
		</div>
	</li>

	<li><a href="/reloadController.do?">Reload</a></li>
</security:authorize>

<security:authorize ifAllGranted="ROLE_USER">
	<li><a href="/about.html" title="About">About</a></li>
</security:authorize>

</ul>