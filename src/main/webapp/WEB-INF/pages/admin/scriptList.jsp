<%@ include file="../taglibs.jsp"%>

<c:set var="buttons">
    <input type="button" class="button"
        onclick="location.href='<c:url value="/scriptController.do?mode=add"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" 
		onclick="location.href='<c:url value="/home.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<display:table name="scriptList" cellspacing="1" cellpadding="1" requestURI="" defaultsort="1" id="scripts" pagesize="50" class="table" export="true">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="scriptForm.name" style="width: 25%" url="/scriptController.do?mode=update" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="scriptForm.description" style="width: 34%"/>
    
    <display:setProperty name="paging.banner.item_name" value="script"/>
    <display:setProperty name="paging.banner.items_name" value="scripts"/> 
        
	<display:setProperty name="export.excel.filename" value="Script List.xls"/>
    <display:setProperty name="export.csv.filename" value="Script List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Script List.pdf"/> 
	
	
</display:table>

<br>

<c:out value="${buttons}" escapeXml="false" />

<br><br>