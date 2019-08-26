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
    <form method="post" <#if currentTask??>action="/tasks/edit/${currentTask.id}"<#else>action="/createTask"</#if> enctype="multipart/form-data">
        <div class="form-group">
        <input type="text" class="form-control" name="description" placeholder="Description" value="<#if currentTask??>${currentTask.description}</#if>"></input>
        </div>
        <div class="form-group">
        <input type="text" class="form-control" name="content" placeholder="Content"  value="<#if currentTask??>${currentTask.content}</#if>"></input>
        </div>

        <div class="custom-file mb-2">
            <input type="file" name="file" class="custom-file-input" id="customFile">
            <label class="custom-file-label" for="customFile">Choose file</label>
            <script>
                //gets the element by its id
                var myFile = document.getElementById('customFile');

                //binds to onchange event of the input field
                myFile.addEventListener('change', function()
                    {
                        //this.files[0].size gets the size of your file.
                        if (this.files[0].size > 10485760)
                        {
                            alert('Размер файла ' + this.files[0].size + ' байт превышает допустимый (10Мб)');
                            myFile.value = null;
                        }
                    }
                );
            </script>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div>
            <button type="submit" class="btn btn-primary"><#if currentTask??>Сохранить<#else>Create</#if></button>
        </div>
    </form>
    </div>
</div>
<#--/Тело-->