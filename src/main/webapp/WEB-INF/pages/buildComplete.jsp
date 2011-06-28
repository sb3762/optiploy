<%@ include file="taglibs.jsp"%>

<form:form commandName="build" id="build">

<table class="table">
		<tr>
			<th align="left"><fmt:message key="buildForm.job.id"/></th>
			<th align="left"><form:input disabled="true" path="jobId" id="jobId"/></th>										
		</tr>
		<tr>
			<th align="left"><fmt:message key="buildForm.job.name"/></th>
			<th align="left"><form:input disabled="true" path="jobName" id="jobName"/></th>										
		</tr>
		<tr>
			<th align="left"><fmt:message key="buildForm.processing.agent"/></th>
			<th align="left"><form:input disabled="true" path="processingAgent" id="processingAgent"/></th>										
		</tr>
		<tr>
			<th align="left"><fmt:message key="buildForm.start.timestamp"/></th>
			<th align="left"><form:input disabled="true" path="startTimestamp" id="startTimestamp"/></th>										
		</tr>
		<tr>
			<th align="left"><fmt:message key="buildForm.complete.timestamp"/></th>
			<th align="left"><form:input disabled="true" path="completeTimestamp" id="completeTimestamp"/></th>										
		</tr>
		<tr>
			<th align="left"><fmt:message key="buildFrom.success"/></th>
			<th align="left"><form:input disabled="true" path="success" id="success"/></th>										
		</tr>
		<tr>
			<th align="left"><fmt:message key="buildForm.build.messages"/></th>
			<th align="left"><form:textarea disabled="true" path="buildMessages" id="buildMessages"/></th>										
		</tr>
		<tr>
			<th align="left"><fmt:message key="buildForm.logFile"/></th>
			<th align="left"><a href="/logFileController.do?logId=${build.logId}">Download</a></th>											
		</tr>
</table>		

</form:form>
