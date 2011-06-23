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

		<ul>
			<li>
			<br>
		        <c:choose>
		            <c:when test="${param.mode == 'profile'}">
						<fmt:message key="userProfile.message"/>		                
		            </c:when>
		            <c:otherwise>
		            	<fmt:message key="userProfile.admin.message"/>    
		            </c:otherwise>
		        </c:choose>
		    </li>
			<li>
					<fmt:message key="user.username"/>
					<form:errors path="username" cssClass="fieldError"/>
					<form:input path="username" id="username" cssClass="bodytext" cssErrorClass="bodytext error"/>										
			</li>
			<c:if test="${param.mode == 'profile' || param.mode == 'add'}">
				<li>
						<fmt:message key="user.password"/> 
						<form:errors path="password" cssClass="fieldError"/>
						<form:password path="password" id="password" cssClass="bodytext" cssErrorClass="bodytext error" showPassword="true"/>
				</li>
				<li>				
						<fmt:message key="user.confirmPassword"/> 
						<form:errors path="confirmPassword" cssClass="fieldError"/>
						<form:password path="confirmPassword" id="confirmPassword" cssClass="bodytext" cssErrorClass="bodytext error" showPassword="true"/>
				</li>
			</c:if>
			<li>				
					<fmt:message key="user.passwordHint"/> 
					<form:errors path="passwordHint" cssClass="fieldError"/>
					<form:input path="passwordHint" id="passwordHint" cssClass="bodytext" cssErrorClass="text large error" />
			</li>
			<li>				
					<fmt:message key="user.firstName"/> 
					<form:errors path="firstName" cssClass="fieldError"/>
					<form:input path="firstName" id="firstName" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>
			<li>				
					<fmt:message key="user.lastName"/> 
					<form:errors path="lastName" cssClass="fieldError"/>
					<form:input path="lastName" id="lastName" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>
			<li>				
					<fmt:message key="user.email"/> 
					<form:errors path="email" cssClass="fieldError"/>
					<form:input path="email" id="email" cssClass="bodytext" cssErrorClass="bodytext error" />
			</li>
			<li>

				<c:choose>
			    <c:when test="${param.mode == 'update' or param.mode == 'add'}">
			    <li>
			        <fieldset>
			            <legend><fmt:message key="userProfile.accountSettings"/></legend>
			            <form:checkbox path="enabled" id="enabled"/>
			            <label for="enabled"><fmt:message key="user.enabled"/></label>
			
			            <form:checkbox path="accountExpired" id="accountExpired"/>
			            <label for="accountExpired"><fmt:message key="user.accountExpired"/></label>
			
			            <form:checkbox path="accountLocked" id="accountLocked"/>
			            <label for="accountLocked"><fmt:message key="user.accountLocked"/></label>			
			        </fieldset>
			    </li>
			    <li>
			        <fieldset class="pickList">
			            <legend><fmt:message key="userProfile.assignRoles"/></legend>
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
			        </fieldset>
			    </li>				
			    </c:when>
			    <c:when test="${not empty user.username}">
			    <li>
			        <strong><fmt:message key="user.roles"/>:</strong>
			        <c:forEach var="role" items="${user.roleList}" varStatus="status">
			            <c:out value="${role.label}"/><c:if test="${!status.last}">,</c:if>
			            <input type="hidden" name="userRoles" value="<c:out value="${role.label}"/>"/>
			        </c:forEach>
			        <form:hidden path="enabled"/>
			        <form:hidden path="accountExpired"/>
			        <form:hidden path="accountLocked"/>			        
			    </li>
			    </c:when>
				</c:choose>
		</ul>
						
				<c:out value="${buttons}" escapeXml="false"/>			
				<br><br>
		</form:form>
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
		
		