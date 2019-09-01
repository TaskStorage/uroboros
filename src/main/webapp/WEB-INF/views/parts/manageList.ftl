<table class="table table-hover mt-3">
    <thead class="bg-secondary">
    <tr>
        <th scope="col" class="col-3.5">Description</th>
        <th scope="col" class="col-4.5">Content</th>
        <th scope="col" class="text-center col-1">Author</th>
        <th scope="col" class="text-center col-1">Attachment</th>
        <th scope="col col-1">Action</th>
        <th scope="col col-1"></th>
    </tr>
    </thead>
    <tbody>
    <#list tasks as task>
        <tr>
            <td>${task.description}</td>
            <td>${task.content}</td>
            <td class="text-center"><a href="/manage/user/${task.author.username}">${(task.author.username)!"&lt;Anonymous&gt;"}</a></td>
            <td class="text-center"><#if task.filename??><a download href="/attachment/${task.filename}">Download</a><#else>N/A</#if></td>
            <td><a class="btn btn-primary btn-sm" href="/manage/edit/${task.id}">Edit</a></td>
            <td>
                <form method="post" action="/manage/delete/${task.id}">
                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </td>
        </tr>
    <#else>
        <tr>
            <td>No records</td>
        </tr>
    </#list>
    </tbody>
</table>