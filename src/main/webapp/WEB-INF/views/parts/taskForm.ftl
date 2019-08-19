<#--Шапка-->
<form method="get">
    <div class="form-row align-items-center">
        <div class="col-auto">
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">Add new task</a>
        </div>
        <div class="col-auto">
            <input type="text" name="searchTag" class="form-control" value="${searchTag?ifExists}" placeholder="Search"/>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </div>
</form>
<#--/Шапка-->
<#--Тело-->
<div class="collapse <#if currentTask??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
    <form method="post" <#if currentTask??>action="/tasks/edit/${currentTask.id}"<#else>action="/createTask"</#if>>
        <div class="form-group">
        <input type="text" class="form-control" name="description" placeholder="Описание" value="<#if currentTask??>${currentTask.description}</#if>"></input>
        </div>
        <div class="form-group">
        <input type="text" class="form-control" name="content" placeholder="Детали"  value="<#if currentTask??>${currentTask.content}</#if>"></input>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div>
            <button type="submit" class="btn btn-primary"><#if currentTask??>Сохранить<#else>Добавить</#if></button>
        </div>
    </form>
    </div>
</div>
<#--/Тело-->