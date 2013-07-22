<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 18/07/13
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="departamentList.title"/></title>
    <meta name="menu" content="DepartamentMenu"/>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key='departamentList.heading'/></h2>

    <form method="get" action="${ctx}/admin/activities" id="searchForm" class="form-search">
        <div id="search" class="input-append">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
            <button id="button.search" class="btn" type="submit">
                <i class="icon-search"></i> <fmt:message key="button.search"/>
            </button>
        </div>
    </form>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/admin/departamentform'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn" href="<c:url value='/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="departamentList" class="table table-condensed table-striped table-hover" requestURI="" id="activities" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="departamentform" media="html"
                        paramId="id" paramProperty="id" titleKey="departament.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="departament.id"/>
        <display:column property="name" sortable="true" titleKey="departament.name"/>
        <display:column property="activities" sortable="true" titleKey="departament.activities"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="departamentList.departament"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="departamentList.departaments"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="departamentList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="departamentList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="departamentList.title"/>.pdf</display:setProperty>
    </display:table>
</div>