<%@ include file="../taglibs.jsp"%>

<c:set var="buttons">
    <input type="button" class="button"
        onclick="location.href='<c:url value="/roleController.do?mode=add"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" 
		onclick="location.href='<c:url value="/home.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<display:table name="roleList" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1" id="roles" pagesize="25" class="table" export="true">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="roleForm.name" style="width: 25%" url="/roleController.do?mode=update" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="roleForm.description" style="width: 34%"/>
    
    <display:setProperty name="paging.banner.item_name" value="role"/>
    <display:setProperty name="paging.banner.items_name" value="roles"/> 	
	
	<display:setProperty name="export.excel.filename" value="Role List.xls"/>
    <display:setProperty name="export.csv.filename" value="Role List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Role List.pdf"/> 

</display:table>

<br>

<c:out value="${buttons}" escapeXml="false" />

<br><br>