<#macro main title>
<html>
<head>
    <@bootstrap.cssAndJs/>
    <@slyak.js url="/webjars/jquery-migrate/jquery-migrate.min.js"/>
    <@slyak.css url="/webjars/font-awesome/web-fonts-with-css/css/fontawesome-all.css"/>
    <@slyak.css url="/css/main.css"/>
</head>
<body>
<style>
    .table th, .table td {
        text-align: center;
        vertical-align: middle !important;
    }
</style>
    <@bootstrap.navbar brand="ITASM" menu=[
    {'title':'项目','url':'/'},
    {'title':'全局设置','url':'/global'}
    ]/>
<div class="container-fluid p-0">
    <div class="title-line">
        <div class="title-line-main">
            <h1>${title}</h1>
            <a class="btn btn-sm btn-primary ml-3">创建项目</a>
        </div>
    </div>
    <hr class="m-0"/>
    <div class="bg-white main">
    <#nested />
    </div>
</div>
</body>
</html>
</#macro>