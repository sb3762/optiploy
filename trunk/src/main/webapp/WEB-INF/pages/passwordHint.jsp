<%@ include file="taglibs.jsp" %>
			
	<br><br>
	
		<h2><fmt:message key="login.passwordHint"/></h2>

	<form name="passwordHint" method="post" action="/passwordHintController.do" >
		 
			<h2><fmt:message key="label.username"/> <input type="text" name="username" value="" tabindex="1"></h2>
		
			<h2><input name="submit" type="submit" value="Submit" tabindex="2"/></h2>
		
	</form>	

