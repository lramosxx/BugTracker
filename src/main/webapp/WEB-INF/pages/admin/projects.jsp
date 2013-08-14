<%--
  Created by IntelliJ IDEA.
  User: luiza
  Date: 15/07/13
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectList.title"/></title>
    <meta name="menu" content="ProjectMenu"/>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="span10">
    <div><h2><fmt:message key='projectList.heading'/></h2></div>

    <form method="get" action="${ctx}/admin/projects" id="searchForm" class="form-search">
        <div id="search" class="input-append">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
            <button id="button.search" class="btn" type="submit">
                <i class="icon-search"></i> <fmt:message key="button.search"/>
            </button>
        </div>
    </form>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/admin/projectform'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn" href="<c:url value='/issuesByUser'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="projectList" class="table table-condensed table-striped table-hover" requestURI="" id="projects" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="projectform" media="html"
                        paramId="id" paramProperty="id" titleKey="project.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="project.id"/>
        <display:column property="name" sortable="true" titleKey="project.name"/>
        <display:column property="description" sortable="true" titleKey="project.description"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="projectList.project"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="projectList.projects"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="projectList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="projectList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="projectList.title"/>.pdf</display:setProperty>
    </display:table>
</div>