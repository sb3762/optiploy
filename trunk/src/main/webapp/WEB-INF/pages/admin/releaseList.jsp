<%@ include file="../taglibs.jsp"%>

<c:set var="buttons">
    <input type="button" class="button"
        onclick="location.href='<c:url value="/releaseController.do?mode=add"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" 
		onclick="location.href='<c:url value="/home.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<display:table name="releaseList" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1" id="releases" pagesize="25" class="table" export="true">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="releaseForm.name" style="width: 25%" url="/releaseController.do?mode=update" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="releaseForm.description" style="width: 34%"/>
    
    <display:setProperty name="paging.banner.item_name" value="release"/>
    <display:setProperty name="paging.banner.items_name" value="releases"/> 	
	
	<display:setProperty name="export.excel.filename" value="Release List.xls"/>
    <display:setProperty name="export.csv.filename" value="Release List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Release List.pdf"/> 

</display:table>

<br>

<c:out value="${buttons}" escapeXml="false" />

<br><br>