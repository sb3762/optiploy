<%@ include file="../taglibs.jsp"%>

<c:set var="buttons">
    <input type="button" class="button"
        onclick="location.href='<c:url value="/functionController.do?mode=add"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" 
		onclick="location.href='<c:url value="/home.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<display:table name="functionList" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1" id="functions" pagesize="25" class="table" export="true">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="functionForm.name" style="width: 25%" url="/functionController.do?mode=update" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="functionForm.description" style="width: 34%"/>
    
    <display:setProperty name="paging.banner.item_name" value="function"/>
    <display:setProperty name="paging.banner.items_name" value="functions"/> 	
	
	<display:setProperty name="export.excel.filename" value="Function List.xls"/>
    <display:setProperty name="export.csv.filename" value="Function List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Function List.pdf"/> 

</display:table>

<br>

<c:out value="${buttons}" escapeXml="false" />

<br><br>