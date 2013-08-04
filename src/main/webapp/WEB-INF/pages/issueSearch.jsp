<%@ page import="java.util.concurrent.TimeUnit" %>
<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 03/08/13
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="issueSearch.title"/></title>
    <meta name="menu" content="IssueMenu"/>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="span12">
    <h2><fmt:message key='issueSearch.heading'/></h2>

    <!--form method="get" action="${ctx}/issues" id="searchForm" class="form-search">
        <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form-->


    <form:form commandName="issuesSearch" method="get" action="/issuesSearch" id="issuesSearch"
               cssClass="well form-vertical" onsubmit="">
        <div class="control-group" style="display:inline-block">
            <appfuse:label styleClass="control-label" key="issueSearch.summary"/>
            <div class="controls">
                <input type="text" id="summary" name="summary" maxlength="100" />
            </div>
        </div>
        <div class="control-group" style="display:inline-block">
            <appfuse:label styleClass="control-label" key="issueSearch.assigned"/>
            <div class="controls">
                <select id="idAssigned" name="idAssigned" >
                    <option selected=""></option>
                    <c:forEach items="${assignedList}" var="assigned">
                        <option value="${assigned.id}">${assigned.firstName}&#32;${assigned.lastName}</option>
                    </c:forEach>
                </select>
            </div>

        </div>
        <div class="control-group" style="display:inline-block">
            <appfuse:label styleClass="control-label" key="issueSearch.reporter"/>
            <div class="controls">
                <select id="idReporter" name="idReporter" >
                    <option selected=""></option>
                    <c:forEach items="${reporterList}" var="reporter">
                        <option value="${reporter.id}">${reporter.firstName}&#32;${reporter.lastName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group" style="display:inline-block">
            <appfuse:label styleClass="control-label" key="issueSearch.project"/>
            <div class="controls">
                <select id="idProject" name="idProject">
                    <option selected=""></option>
                    <c:forEach items="${projectList}" var="project">
                        <option value="${project.id}">${project.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group" style="display:inline-block">
            <appfuse:label styleClass="control-label" key="issueSearch.status"/>
            <div class="controls">
                <select id="idStatus" name="idStatus">
                    <option selected=""></option>
                    <c:forEach items="${statusList}" var="status">
                        <option value="${status.id}">${status.description}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group" style="display:inline-block">
            <div class="controls">
                <input type="submit" value="<fmt:message key="button.filter"/>" class="btn btn-primary" style="margin-top: -10px;"/>
            </div>
        </div>

    </form:form>

    <display:table name="issueList" class="table table-condensed table-striped table-hover" requestURI="" id="issues" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="issueform" media="html"
                        paramId="id" paramProperty="id" titleKey="issue.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="issue.id"/>
        <display:column property="description" sortable="true" titleKey="issue.description"/>
        <display:column property="summary" sortable="true" titleKey="issue.summary"/>
        <display:column property="departament.name" sortable="true" titleKey="issue.departament"/>
        <display:column property="activity.name" sortable="true" titleKey="issue.activity"/>
        <display:column property="reporter.firstName" sortable="true" titleKey="issue.reporter"/>
        <display:column property="assigned.firstName" sortable="true" titleKey="issue.assigned"/>
        <display:column property="status.description" sortable="true" titleKey="issue.status"/>
        <display:column property="expectedDate" sortable="true" titleKey="issue.expectedDate"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="issueList.issue"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="issueList.issues"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="issueList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="issueList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="issueList.title"/>.pdf</display:setProperty>
    </display:table>
</div>