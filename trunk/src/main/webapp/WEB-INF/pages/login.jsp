<%@ include file="taglibs.jsp" %>
<div class="bodytext" style="margin:30px" align="center">			
	<br><br><br>
	<form name="loginForm" method="post" action="/j_spring_security_check" onsubmit="saveUsername(this);return validateForm(this)">
	<ul>
		<c:if test="${param.error != null}">
		   	<li class="bodytext error">
		       <img src="${ctx}/images/iconWarning.gif"/><fmt:message key="errors.password.mismatch"/>		      
		   	</li>
		</c:if>
		    <li>		 
				<fmt:message key="label.username"/> <input type="text" name="j_username" value="" tabindex="1">
			</li>
			<li>
				<fmt:message key="label.password"/> <input type="password" name="j_password" tabindex="2"/>
			</li>
			<li>
				<input type="checkbox" name="_spring_security_remember_me" tabindex="3"/> <fmt:message key="login.rememberMe"/>
			</li>
			<li>
				<input name="login" type="submit" value="<fmt:message key='button.login'/>" tabindex="4"/>
			</li>
	</ul>
	</form>	
</div>	
