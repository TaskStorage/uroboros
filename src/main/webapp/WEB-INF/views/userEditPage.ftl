<#import "parts/boilerplate.ftl" as c>
<@c.page>

    <div class="form-group mt-3">
        <form method="post" action="/users/edit/${user.id}">

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Username:</label>
                <div class="col-sm-5">
                    <input type="text" name="username" value="${user.username}" class="form-control ${(usernameError??)?string('is-invalid', '')}"/>
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
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
            <div>
                <#list roles as role>
                    <div class="form-check form-check-inline">
                        <label><input class="form-check-input position-static" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}> ${role}</label>
                    </div>
                </#list>
            </div>
            <div class="form-check form-check-inline">
                <label><input class="form-check-input position-static" type="checkbox" name="active" <#if user.active?string == "true">checked</#if>> is Active</label>
            </div>

            <input type="hidden" name="userId" value="${user.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <div class="form-group">
                <button type="submit"class="btn btn-primary mt-2">Save</button>
            </div>
        </form>
    </div>

</@c.page>