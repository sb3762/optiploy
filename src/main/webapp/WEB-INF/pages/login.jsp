<%@ include file="taglibs.jsp" %>
		
	<br><br><br>
	
	<form name="loginForm" method="post" action="/j_spring_security_check" onsubmit="saveUsername(this);return validateForm(this)">
	
		<c:if test="${param.error != null}">					   
		       	<h4><img src="${ctx}/images/iconWarning.gif"/><fmt:message key="errors.password.mismatch"/></h4>	   	
		</c:if>		    		 
				<h2><fmt:message key="label.username"/> <input type="text" name="j_username" value="" tabindex="1"></h2>
			
				<h2><fmt:message key="label.password"/> <input type="password" name="j_password" tabindex="2"/></h2>
			
				<h3><input type="checkbox" name="_spring_security_remember_me" tabindex="3"/> <fmt:message key="login.rememberMe"/></h3>
			
				<input name="login" class="button" type="submit" value="<fmt:message key='button.login'/>" tabindex="4"/>
								
				<br>
				
				<h3><a href="/passwordHint.html"><fmt:message key="login.passwordHint"/></a></h3>
				
	</form>	
	
