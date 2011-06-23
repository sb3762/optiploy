<%@ include file="../taglibs.jsp"%>
		
	
		<form:form commandName="job" method="post" id="job" action="jobController.do">

		<c:set var="buttons">
            <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>

        <c:if test="${param.mode == 'update' and param.mode != 'add'}">
            <input type="submit" class="button" name="delete" 
				onclick="bCancel=true;return confirmDelete('job')"
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
		            <fmt:message key="jobForm.admin.message"/>		    
		    </li>			
			<li>				
					<fmt:message key="jobForm.name"/> 
					<form:errors path="name" cssClass="fieldError"/>
					<form:input path="name" id="name" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />
			</li>
			<li>				
					<fmt:message key="jobForm.description"/> 
					<form:errors path="description" cssClass="fieldError"/>
					<form:input path="description" id="description" cssClass="bodytext" cssErrorClass="bodytext error" maxlength="50" />					
			</li>
			<li>	
					<a href="/buildController.do?jobId=${param.id}"><fmt:message key="jobForm.build"/></a>					
			</li>			
		</ul>						
				<c:out value="${buttons}" escapeXml="false"/>
				<br><br>

		</form:form>

		
		