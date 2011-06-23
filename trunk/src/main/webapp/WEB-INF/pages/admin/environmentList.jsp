<%@ include file="../taglibs.jsp"%>

<c:set var="buttons">
    <input type="button" class="button"
        onclick="location.href='<c:url value="/environmentController.do?mode=add"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" 
		onclick="location.href='<c:url value="/home.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<display:table name="environmentList" cellspacing="1" cellpadding="1" requestURI="" defaultsort="1" id="environments" pagesize="50" class="table" export="true">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="environmentForm.name" style="width: 25%" url="/environmentController.do?mode=update" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="environmentForm.description" style="width: 34%"/>
    
    <display:setProperty name="paging.banner.item_name" value="environment"/>
    <display:setProperty name="paging.banner.items_name" value="environments"/> 
        
	<display:setProperty name="export.excel.filename" value="Environment List.xls"/>
    <display:setProperty name="export.csv.filename" value="Environment List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Environment List.pdf"/> 
	
	
</display:table>

<br>

<c:out value="${buttons}" escapeXml="false" />

<br><br>