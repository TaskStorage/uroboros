<#macro access path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Username: </label>
            <div class="col-sm-5">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>" class="form-control ${(usernameError??)?string('is-invalid', '')}" placeholder="User name"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>
        <div class="form-group row">
            <label for="password2" class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-5">
                <input type="password" name="password2" class="form-control ${(password2Error??)?string('is-invalid', '')}" placeholder="Password confirmation"/>
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
        </div>
        </#if>

        <#if isRegisterForm>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Email: </label>
            <div class="col-sm-5">
                <input type="email" name="email" value="<#if user??>${user.email}</#if>" class="form-control ${(emailError??)?string('is-invalid', '')}" placeholder="email@example.com"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <#if !isRegisterForm><a href="/register">Register</a>
                <#else><div><a href="/login">Back to login</a></div></#if>
            </label>

            <div class="col-sm-2 mt-2">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="remember-me" id="defaultCheck">
                    <label class="form-check-label" for="defaultCheck">
                        Remember
                    </label>
                </div>
            </div>

            <div class="col-sm-2">
                <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
            </div>

        </div>

    </form>
</#macro>