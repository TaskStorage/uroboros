<#import "parts/boilerplate.ftl" as c>
<#import "parts/access.ftl" as a>
<@c.page>
    <#if error??>
        <div class="mb-3">${error}</div>
    </#if>
    <@a.access "/register" true/>

</@c.page>