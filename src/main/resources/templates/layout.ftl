<#macro main title>
<html>
<head>
    <@bootstrap.cssAndJs/>
    <@slyak.js url="/webjars/jquery-migrate/jquery-migrate.min.js"/>
    <@slyak.css url="/webjars/font-awesome/web-fonts-with-css/css/fontawesome-all.css"/>
</head>
<body>
<style>
    .table th, .table td {
        text-align: center;
        vertical-align: middle!important;
    }
</style>
    <@bootstrap.navbar brand="<b style='color:#333'>IT自动化管理</b>" menu=[
    {'title':'项目','url':'/'},
    {'title':'全局设置','url':'/global'}
    ]/>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <div class="col-12">
        <h1 class="mt-1 mb-1">${title}</h1>
        <#nested />
        </div>
    </div>
</div>
</body>
</html>
</#macro>