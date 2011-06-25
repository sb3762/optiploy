<%@ include file="../taglibs.jsp"%>
		
	
		<form:form commandName="script" method="post" id="script" action="scriptController.do">

		<c:set var="buttons">
            <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>

        <c:if test="${param.mode == 'update' and param.mode != 'add'}">
            <input type="submit" class="button" name="delete" 
				onclick="bCancel=true;return confirmDelete('script')"
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
			<fmt:message key="scriptForm.admin.message"/>
			<br>
			<table class="table">
	   			<tr>
	   				<th align="left"><fmt:message key="scriptForm.name"/></th>
	   				<td align="left"><form:errors path="name" cssClass="fieldError"/><form:input path="name" id="name"/></td>
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="scriptForm.description"/></th>
	   				<td align="left"><form:errors path="description" cssClass="fieldError"/><form:input path="description" id="description" /></td>
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="scriptForm.fileName"/></th>
	   				<td align="left"><form:errors path="fileName" cssClass="fieldError"/><form:input path="fileName" id="fileName" /></td>
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="scriptForm.version"/></th>
	   				<td align="left"><form:errors path="version" cssClass="fieldError"/><form:input path="version" id="version" /></td>
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="scriptForm.content"/></th>
	   				<td align="left"><form:errors path="content" cssClass="fieldError"/><form:textarea path="content" id="content" /></td>
	   			</tr>
	   		</table>
	   		
  			<br><br>
  									
			<c:out value="${buttons}" escapeXml="false"/>
			
			<br><br>			
		</center>			
	   	</c:if>			

		</form:form>

		
		