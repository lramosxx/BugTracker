<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 27/07/13
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="statusList.title"/></title>
    <meta name="menu" content="StatusMenu"/>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key='statusList.heading'/></h2>

    <form method="get" action="${ctx}/admin/status" id="searchForm" class="form-search">
        <div id="search" class="input-append">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
            <button id="button.search" class="btn" type="submit">
                <i class="icon-search"></i> <fmt:message key="button.search"/>
            </button>
        </div>
    </form>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/admin/statusform'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn" href="<c:url value='/issuesByUser'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="statusList" class="table table-condensed table-striped table-hover" requestURI="" id="status" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="statusform" media="html"
                        paramId="id" paramProperty="id" titleKey="status.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="status.id"/>
        <display:column property="description" sortable="true" titleKey="status.description"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="statusList.status"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="statusList.status"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="statusList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="statusList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="statusList.title"/>.pdf</display:setProperty>
    </display:table>
</div>