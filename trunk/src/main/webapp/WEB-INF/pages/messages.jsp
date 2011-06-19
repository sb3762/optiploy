<%@ include file="taglibs.jsp"%>

<%-- Error Messages --%>
<c:if test="${not empty errors}">
    <div class= middle id="errorMessages">
    	<h4>
        <c:forEach var="error" items="${errors}">
            <img src="<c:url value="${ctx}/images/iconWarning.gif"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}"/><br />
        </c:forEach>
        </h4>
    </div>
    <c:remove var="errors"/>
</c:if>

<%-- Success Messages --%>
<c:if test="${not empty successMessages}">
    <div id="successMessages">
    	<h2>
        <c:forEach var="msg" items="${successMessages}">
            <img src="<c:url value="${ctx}/images/iconInformation.gif"/>"
                alt="<fmt:message key="icon.information"/>" class="icon" />
            <c:out value="${msg}"/><br />
        </c:forEach>
        </h2>
    </div>
    <c:remove var="successMessages" scope="session"/>
</c:if>
