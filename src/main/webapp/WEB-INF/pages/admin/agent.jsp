<%@ include file="../taglibs.jsp"%>
		
	
		<form:form commandName="agent" method="post" id="agent" action="agentController.do">

		<c:set var="buttons">
            <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>

        <c:if test="${param.mode == 'update' and param.mode != 'add'}">
            <input type="submit" class="button" name="delete" 
				onclick="bCancel=true;return confirmDelete('agent')"
                value="<fmt:message key="button.delete"/>"/>
        </c:if>

            <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>"/>
        </c:set>		

		<c:if test="${param.mode == 'update'}">            
			<input type="hidden" name="id" value="${param.id}"/>
			<input type="hidden" name="mode" value="${param.mode}"/>			
        </c:if>

		<ul>
			<li>
		            <fmt:message key="agentForm.admin.message"/>		    
		    </li>			
			<li>				
					<fmt:message key="agentForm.name"/> 
					<form:errors path="name" cssClass="fieldError"/>
					<form:input path="name" id="name" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>
			<li>				
					<fmt:message key="agentForm.description"/> 
					<form:errors path="description" cssClass="fieldError"/>
					<form:input path="description" id="description" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>	
			<li>				
					<fmt:message key="agentForm.status"/> 
					<form:errors path="status" cssClass="fieldError"/>
					<form:input disabled="true" path="status" id="status" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>	
			<li>				
					<fmt:message key="agentForm.priority"/> 
					<form:errors path="priority" cssClass="fieldError"/>
					<form:input disabled="true" path="priority" id="priority" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>	
			<li>				
					<fmt:message key="agentForm.address"/> 
					<form:errors path="address" cssClass="fieldError"/>
					<form:input disabled="true" path="address" id="address" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>	
			<li>				
					<fmt:message key="agentForm.port"/> 
					<form:errors path="port" cssClass="fieldError"/>
					<form:input disabled="true" path="port" id="port" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>
			<li>				
					<fmt:message key="agentForm.version"/> 
					<form:errors path="version" cssClass="fieldError"/>
					<form:input disabled="true" path="version" id="version" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>
		</ul>						
				<c:out value="${buttons}" escapeXml="false"/>
				<br><br>

		</form:form>

		
		