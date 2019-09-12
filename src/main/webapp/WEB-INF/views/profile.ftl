<#import "parts/boilerplate.ftl" as c>

<@c.page>
    <h5>${user.username}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-5">
                <input type="text" name="email" value="${(user.email)!"&lt;unknown&gt;"}" class="form-control ${(emailError??)?string('is-invalid', '')}"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</@c.page>