<%--
  Created by IntelliJ IDEA.
  User: luiza
  Date: 15/07/13
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectDetail.title"/></title>
    <meta name="menu" content="ProjectMenu"/>
</head>

<div class="span2">
    <h2><fmt:message key='projectDetail.heading'/></h2>
</div>
<div class="span7">
    <form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
    <form:form commandName="project" method="post" action="/admin/projectform" id="projectForm"
               cssClass="well form-horizontal">
        <form:hidden path="id"/>

        <div class="control-group">
            <appfuse:label styleClass="control-label" key="project.name"/>
            <div class="controls">
                <form:input path="name" id="name" maxlength="50"/>
                <form:errors path="name" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="project.description"/>
            <div class="controls">
                <form:input path="description" id="description" maxlength="50"/>
                <form:errors path="description" cssClass="help-inline"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary" name="save">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty project.id}">
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
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['projectForm']).focus();
    });
</script>