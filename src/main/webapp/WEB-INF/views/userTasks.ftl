<#import "parts/boilerplate.ftl" as c>
<@c.page>

    <#if isCurrentUser>
        <#include "parts/taskForm.ftl" />
    </#if>
    <#include "parts/taskList.ftl">

</@c.page>