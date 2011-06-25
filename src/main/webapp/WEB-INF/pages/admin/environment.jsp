<%@ include file="../taglibs.jsp"%>
		
	
		<form:form commandName="environment" method="post" id="environment" action="environmentController.do">

		<c:set var="buttons">
            <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>

        <c:if test="${param.mode == 'update' and param.mode != 'add'}">
            <input type="submit" class="button" name="delete" 
				onclick="bCancel=true;return confirmDelete('environment')"
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
			<fmt:message key="environmentForm.admin.message"/>
			<br>
			<table class="table">
	   			<tr>
	   				<th align="left"><fmt:message key="environmentForm.name"/></th>
	   				<td align="left"><form:errors path="name" cssClass="fieldError"/><form:input path="name" id="name"/></td>
	   			</tr>
	   			<tr>
	   				<th align="left"><fmt:message key="environmentForm.description"/></th>
	   				<td align="left"><form:errors path="description" cssClass="fieldError"/><form:input path="description" id="description" cssClass="fieldError" /></td>
	   			</tr>
	   		</table>
	   		
  			<br><br>
  									
			<c:out value="${buttons}" escapeXml="false"/>
			
			<br><br>			
		</center>			
	   	</c:if>	

		</form:form>

		
		