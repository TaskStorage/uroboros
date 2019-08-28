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
<div class="collapse <#if task??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" <#if task?? && task.id !=0>action="/tasks/edit/${task.id}"<#else>action="/createTask"</#if> enctype="multipart/form-data">
            <div class="form-group">
                <input class="form-control ${(descriptionError??)?string('is-invalid', '')}" type="text" name="description"
                       value="<#if task??>${task.description}</#if>" placeholder="Enter description">
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input class="form-control ${(contentError??)?string('is-invalid', '')}" name="content" value="<#if task??>${task.content}</#if>"
                       placeholder="Details"></input>
                <#if contentError??>
                    <div class="invalid-feedback">
                        ${contentError}
                    </div>
                </#if>
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
            <input type="hidden" name="id" value="<#if task??>${task.id}<#else>0</#if>"/>
            <div>
                <button type="submit" class="btn btn-primary"><#if task??>Save<#else>Create</#if></button>
            </div>
        </form>
    </div>
</div>
<#--/Тело-->