<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 18/07/13
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="activityDetail.title"/></title>
    <meta name="menu" content="ActivityMenu"/>
</head>

<div class="span3">
    <h2><fmt:message key='activityDetail.heading'/></h2>
</div>
<div class="span7">
    <form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
    <form:form commandName="activity" method="post" action="/admin/activityform" id="activityForm"
               cssClass="well form-horizontal">
        <form:hidden path="id"/>

        <div class="control-group">
            <appfuse:label styleClass="control-label" key="activity.name"/>
            <div class="controls">
                <form:input path="name" id="name" maxlength="100"/>
                <form:errors path="name" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="activity.hoursToYellow"/>
            <div class="controls">
                <form:input path="hoursToYellow" id="hoursToYellow" maxlength="5"/>
                <form:errors path="hoursToYellow" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="activity.hoursToRed"/>
            <div class="controls">
                <form:input path="hoursToRed" id="hoursToRed" maxlength="5"/>
                <form:errors path="hoursToRed" cssClass="help-inline"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary" name="save">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty activity.id}">
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
        $("input[type='text']:visible:enabled:first", document.forms['activityForm']).focus();
    });
</script>