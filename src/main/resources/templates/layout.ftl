<#macro html title>
<html>
<head>
    <@bootstrap.cssAndJs/>
    <@slyak.js url="/webjars/jquery-migrate/jquery-migrate.min.js"/>
    <@slyak.css url="/webjars/font-awesome/web-fonts-with-css/css/fontawesome-all.css"/>
    <@slyak.css url="/css/main.css"/>
</head>
<body>
    <@bootstrap.navbar brand="ITASM" menu=[
    <#--{'title':'资源站','url':'/resources'},-->
    {'title':'项目','url':'/'},
    {'title':'全局设置','url':'/global'},
    {'title':'使用帮助','url':'/help'}
    ]/>
<div class="container-fluid p-0">
    <div class="bg-white">
        <#nested />
    </div>
</div>
</body>
</html>
</#macro>

<#macro titleLine title btnCreate={}>
<div class="title-line">
    <div class="title-line-main">
        <h1>${title}</h1>
        <#if btnCreate.title??>
            <a class="btn btn-sm btn-primary ml-3"
               href="<@slyak.query url="${btnCreate.url}"/>">${btnCreate.title}</a>
        </#if>
    </div>
</div>
</#macro>

<#macro main title btnCreate={}>
    <@html title=title>
        <@titleLine title=title btnCreate=btnCreate/>
    <div class="main">
        <#nested />
    </div>
    </@html>
</#macro>


<#macro rightMain left right title btnCreate={}>
    <@html title=title>
    <div class="layout-l-r">
        <div class="layout-cell sidebar left">
        ${left}
        </div>
        <div class="layout-cell">
            <@titleLine title=title btnCreate=btnCreate/>
            <div class="p-3">
            ${right}
            </div>
        </div>
    </div>
    </@html>
</#macro>

<#macro leftMain left right title btnCreate={}>
    <@html title=title>
    <div class="layout-l-r">
        <div class="layout-cell left">
            <@titleLine title=title btnCreate=btnCreate/>
            <div class="p-3">
            ${left}
            </div>
        </div>
        <div class="layout-cell sidebar">
            ${right}
        </div>
    </div>
    </@html>
</#macro>