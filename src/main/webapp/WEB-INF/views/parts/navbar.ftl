<#import "logout.ftl" as l>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand text-white" href="/">
        <img src="/resources/logo.png" width="50" height="30" class="d-inline-block align-left" alt=""/>
        TaskStorage
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/tasks">Tasks</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/users">Users</span></a>
            </li>
        </ul>
        <a href="/login" class="btn btn-primary btn-sm">Login</a>
        <a href="/register" class="btn btn-primary btn-sm ml-1">Register</a>
        <@l.logout/>
    </div>
</nav>