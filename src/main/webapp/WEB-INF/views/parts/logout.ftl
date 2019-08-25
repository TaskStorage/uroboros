<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary btn-sm ml-1" type="submit">Sign Out</button>
    </form>
</#macro>