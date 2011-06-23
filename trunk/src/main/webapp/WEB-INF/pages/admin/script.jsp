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

		<ul>
			<li>
		            <fmt:message key="scriptForm.admin.message"/>		    
		    </li>			
			<li>				
					<fmt:message key="scriptForm.name"/> 
					<form:errors path="name" cssClass="fieldError"/>
					<form:input path="name" id="name" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>
			<li>				
					<fmt:message key="scriptForm.description"/> 
					<form:errors path="description" cssClass="fieldError"/>
					<form:input path="description" id="description" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>			
		</ul>						
				<c:out value="${buttons}" escapeXml="false"/>
				<br><br>

		</form:form>

		
		