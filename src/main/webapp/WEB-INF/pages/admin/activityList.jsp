<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 18/07/13
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="activityList.title"/></title>
    <meta name="menu" content="ActivityMenu"/>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key='activityList.heading'/></h2>

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
        <a class="btn btn-primary" href="<c:url value='/admin/activityform'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn" href="<c:url value='/issuesByUser'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="activityList" class="table table-condensed table-striped table-hover" requestURI="" id="activities" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="activityform" media="html"
                        paramId="id" paramProperty="id" titleKey="activity.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="activity.id"/>
        <display:column property="name" sortable="true" titleKey="activity.name"/>
        <display:column property="hoursToYellow" sortable="true" titleKey="activity.hoursToYellow"/>
        <display:column property="hoursToRed" sortable="true" titleKey="activity.hoursToRed"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="activityList.activity"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="activityList.activities"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="activityList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="activityList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="activityList.title"/>.pdf</display:setProperty>
    </display:table>
</div>