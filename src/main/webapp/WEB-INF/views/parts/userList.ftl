<table class="table table-sm">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Role</th>
        <th scope="col">isActive</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td>${user.active?string}</td>
            <td><a href="/users/edit/${user.id}">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>