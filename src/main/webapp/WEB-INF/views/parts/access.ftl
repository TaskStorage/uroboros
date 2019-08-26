<#macro access path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Username: </label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="username" placeholder="Username"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <#if !isRegisterForm><a href="/register">Register</a>
                <#else><div><a href="/login">Back to login</a></div></#if>
            </label>
            <div class="col-sm-5">
                <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
            </div>
        </div>
    </form>
</#macro>