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
    <title><fmt:message key="issueList.title"/></title>
    <meta name="menu" content="IssueMenu"/>
    <style rel="stylesheet" type="text/css">
    .label-danger {
    display: inline-block;
    padding: 2px 4px;
    font-size: 11.844px;
    font-weight: bold;
    line-height: 14px;
    color: #fff;
    text-shadow: 0 -1px 0 rgba(0,0,0,0.25);
    white-space: nowrap;
    vertical-align: baseline;
    background-color: #E25454;
    }
    </style>

</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>



<div class="span12">
    <h2><fmt:message key='issueList.heading'/></h2>


    <form method="get" action="${ctx}/issuesByUser" id="searchForm" class="form-search">
        <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <div id="actions" class="form-actions">
        <div class="control-group" style="display:inline-block">
        <a class="btn btn-primary" href="<c:url value='/issueform'/>" id="btnAdd">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        </div>
        <div class="control-group" style="display:inline-block">
        <a class="btn btn-primary" href="<c:url value='/issuesByUser?q=assigned'/>">
            <i class=""></i> <fmt:message key="button.issue.assigned"/></a>
        </div>
        <div class="control-group" style="display:inline-block">
        <a class="btn btn-primary" href="<c:url value='/issuesByUser?q=myIssues'/>">
             <i class=""></i> <fmt:message key="button.issue.my"/></a>
        </div>
        <div class="control-group" style="display:inline-block">
        <a class="btn btn-primary" href="<c:url value='/issuesSearch'/>">
            <i class="icon-search icon-white"></i> <fmt:message key="button.search"/></a>
        </div>
        <div cssClass="control-group" style="display:inline-block;float: right;">
            <div id="error" style="visible:false;">
                <span style="color:red;"><fmt:message key="selectAProject.message"/></span>
            </div>
            <div class="form-controls">
                <select id="idProject" name="idProject">
                    <option selected value=""><fmt:message key="projectByUser.Combo"/></option>
                    <c:forEach items="${projectsByUserList}" var="p">
                        <option value="${p.id}" ${p.id == currentProject.id ? "selected" : ""}>${p.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>


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

            <display:column sortable="true" titleKey="issue.time">
                <div id="imgStatus${issues.id}" props="${issues.activity.hoursToYellow}|${issues.activity.hoursToRed}|${issues.expectedDate}"></div>
            </display:column>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="issueList.issue"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="issueList.issues"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="issueList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="issueList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="issueList.title"/>.pdf</display:setProperty>
    </display:table>
</div>

<script>

    $(document).ready(function(){
        $("#idProject").change(function(){
            //alert(window.location.pathname);
            $.ajax({
                url : "/issuesByUser?idProject="+this.value,
                async : false,
                success: function(content) {
                    $("body").html(content);
                }
            });

            if ($("#idProject").val() != ""){
                $("#error").hide();
            }
        });

        $("#error").hide();
        $("#btnAdd").click(function(){
            return hideOrShowMessage();
        });

        $("a[href^='issueform']").each(function(a,b){
            $(this).click(function(){
                return hideOrShowMessage();
            });

        });

        calculateImgStatus();
    });

    function hideOrShowMessage(){
        if ($("#idProject").val() == ""){
            $("#error").show();
            return false;
        }
        else{
            $("#error").hide();
            return true;
        }
    }


    function calculateImgStatus(){

        $("div[id^='imgStatus']").each(function(){
            var dt1       = new Date();
            var dt2       = new Date($(this).attr("props").split("|")[2]);
            var yellow    = $(this).attr("props").split("|")[0];
            var red       = $(this).attr("props").split("|")[1];
            var hoursDiff = parseInt((dt2-dt1)/(3600*1000));

            if (hoursDiff <= yellow){
                $(this).html("<span class='label label-danger'><fmt:message key='expectative.red'/></span>");
            }
            else{
                if (hoursDiff > yellow && hoursDiff <= red)
                    $(this).html("<span class='label label-warning'><fmt:message key='expectative.yellow'/></span>");
                else
                    $(this).html("<span class='label label-success'><fmt:message key='expectative.green'/></span>");
            }
//            alert(parseInt((dt2-dt1)/(3600*1000)));

        });

    }

</script>