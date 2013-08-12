<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 03/08/13
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="issueDetail.title"/></title>
    <meta name="menu" content="IssueMenu"/>
    <link rel="stylesheet" type="text/css" media="all" link href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
</head>

<div class="span3">
    <h2><fmt:message key='issueDetail.heading'/></h2>
</div>
<div class="span7">
    <form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
    <form:form commandName="issue" method="post" action="/issueform" id="issueForm"
               cssClass="well form-horizontal" onsubmit="">
        <form:hidden path="id"/>

        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.summary"/>
            <div class="controls">
                <form:input path="summary" id="summary" maxlength="100"/>
                <form:errors path="summary" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.description"/>
            <div class="controls">
                <form:textarea path="description" id="description" maxlength="2000" rows="5" class="span11"/>
                <form:errors path="description" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.departament"/>
            <div class="controls">
                <select id="departament" name="departament" class="span11">
                    <option selected></option>
                    <c:forEach items="${departamentList}" var="depto">
                            <option value="${depto.id}" ${issue.departament.id eq depto.id ? " selected" : ""} >${depto.name}</option>
                    </c:forEach>
                </select>
                <form:errors path="departament" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.activity"/>
            <div class="controls">
                <select id="activity" name="activity" class="span11">
                    <option selected></option>
                    <c:forEach items="${activityList}" var="a">
                        <option value="${a.id}" ${issue.activity.id eq a.id ? " selected" : ""} >${a.name}</option>
                    </c:forEach>
                </select>
                <form:errors path="activity" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.project"/>
            <div class="controls">
                <select id="project" name="project" class="span11">
                    <option selected></option>
                    <c:forEach items="${projectList}" var="p">
                        <option value="${p.id}" ${issue.project.id eq p.id ? " selected" : ""} >${p.name}</option>
                    </c:forEach>
                </select>
                <form:errors path="project" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.assigned"/>
            <div class="controls">
                <select id="assigned" name="assigned" class="span6">
                    <option selected=""></option>
                    <c:forEach items="${userList}" var="u">
                        <option value="${u.id}" ${issue.assigned.id eq u.id ? " selected" : "" } >${u.firstName}&#32;${u.lastName}</option>
                    </c:forEach>
                </select>
                <form:errors path="assigned" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.status"/>
            <div class="controls">
                <select id="status" name="status" class="span6">
                    <option selected=""></option>
                    <c:forEach items="${statusList}" var="s">
                        <option value="${s.id}" ${issue.status.id eq s.id ? " selected" : ""} >${s.description}</option>
                    </c:forEach>
                </select>
                <form:errors path="status" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.expectedDate" />
            <div class="controls">
                <form:input path="expectedDate" id="expectedDate" class="span6"/>
                <form:errors path="expectedDate" cssClass="help-inline"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary" name="save">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty issue.id}">
                <button type="submit" class="btn" name="delete">
                    <i class="icon-trash"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>
            <button type="submit" class="btn" name="cancel">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<script>
    $(function() {
        $( "#expectedDate" ).datepicker();
    });
</script>
