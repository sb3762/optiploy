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

		<c:if test="${param.mode == 'update' or param.mode == 'add'}">				
		<center>
			<fmt:message key="jobForm.admin.message"/>
			<br>
			<table class="table">
	   			<tr>
	   				<th align="left"><fmt:message key="agentForm.name"/></th>
	   				<td align="left"><form:errors path="name" cssClass="fieldError"/><form:input path="name" id="name"/></td>
	   			</tr>
	   			<tr>	
	   				<th align="left"><fmt:message key="agentForm.description"/></th> 
					<th align="left"><form:errors path="description" cssClass="fieldError"/><form:input path="description" id="description" cssClass="fieldError"/></th>
	   			</tr>
	   			<tr>	
	   				<th align="left"><fmt:message key="agentForm.instances"/></th> 
					<th align="left"><form:errors path="instances" cssClass="fieldError"/><form:input path="instances" id="instances" cssClass="fieldError"/></th>
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="agentForm.status"/></th>  
					<th align="left"><form:errors path="status" cssClass="fieldError"/><form:input disabled="true" path="status" id="status" cssClass="fieldError" /></th> 
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="agentForm.priority"/></th>  
					<th align="left"><form:errors path="priority" cssClass="fieldError"/><form:input disabled="true" path="priority" id="priority" cssClass="fieldError" /></th> 
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="agentForm.address"/></th>  
					<th align="left"><form:errors path="address" cssClass="fieldError"/><form:input disabled="true" path="address" id="address" cssClass="fieldError" /></th> 
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="agentForm.port"/></th>  
					<th align="left"><form:errors path="port" cssClass="fieldError"/><form:input disabled="true" path="port" id="port" cssClass="fieldError" /></th> 
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="agentForm.version"/></th>  
					<th align="left"><form:errors path="version" cssClass="fieldError"/><form:input disabled="true" path="version" id="version" cssClass="fieldError" /></th> 
	   			</tr>	   			
	   		</table>
	   		
  			<br><br>
  									
			<c:out value="${buttons}" escapeXml="false"/>
			
			<br><br>			
		</center>		
		</c:if>
		
		</form:form>

		
		