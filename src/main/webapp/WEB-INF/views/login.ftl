<#import "parts/boilerplate.ftl" as c>
<#import "parts/access.ftl" as a>
<@c.page>
<#--Стасус активации-->
<#--Из RegistrationController success и danger стили-->
    <#if messageType??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
    <@a.access "/login" false/>

</@c.page>