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
<#--Сообщение при принудительном окончании сессии-->
    <#if message??>
        <div class="mb-3">${message}</div>
    </#if>

    <@a.access "/login" false/>

</@c.page>