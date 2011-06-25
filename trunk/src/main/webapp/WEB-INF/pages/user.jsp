<%@ include file="taglibs.jsp"%>

<div style="margin:30px" align="center">			
	
		<form:form commandName="user" name="user" method="post" id="user" action="javascript:saveData();">
		
		<c:set var="buttons">
            <input type="submit" class="button" name="save" value="<fmt:message key="button.save"/>"/>

        <c:if test="${param.mode == 'update' and (param.mode != 'add' or param.mode != 'profile')}">
            <input type="submit" class="button" name="delete" 
				onclick="javascript:isDelete();return confirmDelete('user')"
                value="<fmt:message key="button.delete"/>"/>
        </c:if>

            <input type="submit" class="button" name="cancel" onclick="javascript:isCancel()" value="<fmt:message key="button.cancel"/>"/>
        </c:set>		
                
        <c:if test="${param.mode == 'update'}">
        	<input type="hidden" name="id" value="${param.id}"/>			
			<form:hidden path="password"/>
			<form:hidden path="confirmPassword"/>		
        </c:if>
        
		<center>			
	        <c:choose>
	            <c:when test="${param.mode == 'profile'}">
					<fmt:message key="userProfile.message"/>		                
	            </c:when>
	            <c:otherwise>
	            	<fmt:message key="userProfile.admin.message"/>    
	            </c:otherwise>
	        </c:choose>
		    <br>
			<table class="table">
				<tr>
					<th align="left"><fmt:message key="user.username"/></th>
					<th align="left"><form:errors path="username" cssClass="fieldError"/><form:input path="username" id="username"/></th>										
				</tr>
			<c:if test="${param.mode == 'profile' || param.mode == 'add'}">
				<tr>
					<th align="left"><fmt:message key="user.password"/></th> 
					<th align="left"><form:errors path="password" cssClass="fieldError"/><form:password path="password" id="password" showPassword="true"/></th>
				</tr>
				<tr>				
					<th align="left"><fmt:message key="user.confirmPassword"/></th>
					<th align="left"><form:errors path="confirmPassword" cssClass="fieldError"/><form:password path="confirmPassword" id="confirmPassword" showPassword="true"/></th>
				</tr>
			</c:if>
				<tr>				
					<th align="left"><fmt:message key="user.passwordHint"/></th> 
					<th align="left"><form:errors path="passwordHint" cssClass="fieldError"/><form:input path="passwordHint" id="passwordHint"/></th>
				</tr>
				<tr>				
					<th align="left"><fmt:message key="user.firstName"/></th> 
					<th align="left"><form:errors path="firstName" cssClass="fieldError"/><form:input path="firstName" id="firstName"/></th>
				</tr>
				<tr>				
					<th align="left"><fmt:message key="user.lastName"/></th> 
					<th align="left"><form:errors path="lastName" cssClass="fieldError"/><form:input path="lastName" id="lastName"/></th>
				</tr>
				<tr>				
					<th align="left"><fmt:message key="user.email"/></th> 
					<th align="left"><form:errors path="email" cssClass="fieldError"/><form:input path="email" id="email"/></th> 
				</tr>
			<c:choose>
		    	<c:when test="${param.mode == 'update' or param.mode == 'add'}">
			    <tr>			        
			        <th align="left"><fmt:message key="userProfile.accountSettings"/></th>
			        <th align="left"> 
			            <form:checkbox path="enabled" id="enabled"/>
			            <label for="enabled"><fmt:message key="user.enabled"/></label>
			
			            <form:checkbox path="accountExpired" id="accountExpired"/>
			            <label for="accountExpired"><fmt:message key="user.accountExpired"/></label>
			
			            <form:checkbox path="accountLocked" id="accountLocked"/>
			            <label for="accountLocked"><fmt:message key="user.accountLocked"/></label>
			        </th>			        
			    </tr>
			    <tr>
		            <table class="pickList">
		                <tr>
		                    <th class="pickLabel">
								<fmt:message key="user.availableRoles"/>			                        
		                    </th>
		                    <td></td>
		                    <th class="pickLabel">
								<fmt:message key="user.roles"/>			                        
		                    </th>
		                </tr>
		                <c:set var="leftList" value="${availableRoles}" scope="request"/>
		                <c:set var="rightList" value="${user.roleList}" scope="request"/>
		                <c:import url="/WEB-INF/pages/pickList.jsp">
		                    <c:param name="listCount" value="1"/>
		                    <c:param name="leftId" value="availableRoles"/>
		                    <c:param name="rightId" value="userRoles"/>
		                </c:import>
		            </table>			       
			    </tr>				
			    </c:when>
			    <c:when test="${not empty user.username}">
			    <tr>	
			        <strong><fmt:message key="user.roles"/>:</strong>
			        <c:forEach var="role" items="${user.roleList}" varStatus="status">
			            <c:out value="${role.label}"/><c:if test="${!status.last}">,</c:if>
			            <input type="hidden" name="userRoles" value="<c:out value="${role.label}"/>"/>
			        </c:forEach>			        
			        <form:hidden path="enabled"/>
			        <form:hidden path="accountExpired"/>
			        <form:hidden path="accountLocked"/>
			    </tr>    				   
			    </c:when>
			</c:choose>
		</table>
						
				<c:out value="${buttons}" escapeXml="false"/>			
				<br><br>
		</form:form>
	</center>
</div>
<SCRIPT language="JavaScript">

var confirmDelete = false;

var confirmCancel = false;

	function saveData()
	{	
		<c:if test="${param.mode == 'update' || param.mode == 'add'}">	
			getListBoxValues('userRoles');
		</c:if>	
		
		if(confirmDelete)
			document.user.action = "userController.do?delete=true";
		else if(confirmCancel)	
			document.user.action = "userController.do?cancel=true";
		else
			document.user.action = "userController.do";
				
		document.user.submit();
	}
	function isDelete()
	{
		confirmDelete = true;
	}
	function isCancel()
	{
		confirmCancel = true;
	}
</SCRIPT>
		
		