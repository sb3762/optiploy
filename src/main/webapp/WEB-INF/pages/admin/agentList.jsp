<%@ include file="../taglibs.jsp"%>
<c:set var="buttons">
    <input type="button" class="button"
        onclick="location.href='<c:url value="/agentController.do?mode=add"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" 
		onclick="location.href='<c:url value="/home.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<display:table name="agentList" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1" id="agents" pagesize="50" class="table" export="true">
    
    <display:column property="name" escapeXml="true" sortable="true" titleKey="agentForm.name" url="/agentController.do?mode=update" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="agentForm.description" />
    <display:column property="instances" escapeXml="true" sortable="true" titleKey="agentForm.instances" />
    <display:column property="status" escapeXml="true" sortable="true" titleKey="agentForm.status" />
    <display:column property="priority" escapeXml="true" sortable="true" titleKey="agentForm.priority" />
    <display:column property="address" escapeXml="true" sortable="true" titleKey="agentForm.address" />
    <display:column property="port" escapeXml="true" sortable="true" titleKey="agentForm.port" />
    <display:column property="version" escapeXml="true" sortable="true" titleKey="agentForm.version" />
    
    <display:setProperty name="paging.banner.item_name" value="agent"/>
    <display:setProperty name="paging.banner.items_name" value="agents"/> 
        
	<display:setProperty name="export.excel.filename" value="Agent List.xls"/>
    <display:setProperty name="export.csv.filename" value="Agent List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Agent List.pdf"/> 
	
	
</display:table>

<br>

<center><c:out value="${buttons}" escapeXml="false" /></center>

<br><br>