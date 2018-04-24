<#-- @ftlvariable name="slyakRequestContext" type="com.slyak.web.support.freemarker.SlyakRequestContext" -->
<#macro html>
<html>
<head>
    <@bootstrap.cssAndJs/>
    <@slyak.js url="/webjars/jquery-migrate/jquery-migrate.min.js"/>
    <@slyak.css url="/webjars/font-awesome/web-fonts-with-css/css/fontawesome-all.css"/>
    <@slyak.css url="/css/main.css"/>
</head>
<body>
    <@bootstrap.navbar brand="ITASM" left=[
    {'title':'项目','url':'/'},
    {'title':'镜像仓库','url':'/mirrors'}
    ] right=[
    {'title':'<i class="fa fa-question-circle fa-lg"></i>','url':'/help'},
    {'title':'<i class="fa fa-cog fa-lg"></i>','url':'/admin'}
    ]/>
<div class="container-fluid p-0">
    <#nested />
</div>
</body>
</html>
</#macro>

<#macro footer>
<div class="footer">
    Slyak ITASM v0.1.0&nbsp;&nbsp;&nbsp;&nbsp;stormning@163.com&nbsp;&nbsp;&nbsp;&nbsp;slyak.com
    <div class="logo">Slyak</div>
</div>
</#macro>

<#macro titleLine title btnCreate={}>
<div class="title-line">
    <div class="title-line-main">
        <h1>${title}</h1>
        <#if btnCreate.title??>
            <a class="btn btn-sm ml-3"
               href="<@slyak.query url="${btnCreate.url}"/>">${btnCreate.title}</a>
        </#if>
    </div>
</div>
</#macro>

<#macro main title btnCreate={}>
    <@html>
        <@titleLine title=title btnCreate=btnCreate/>
    <div class="main">
        <#nested />
    </div>
    </@html>
</#macro>


<#macro rightMain left right title btnCreate={}>
    <@html>
    <div class="layout-l-r bg-white">
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
    <@html>
    <div class="layout-l-r bg-white">
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

<#macro list title="" items=[{'title':'示例','url':'/','class':'fa-plus'}]>
<div class="list-wrapper">
    <#if title?has_content>
        <div class="list-title">${title}</div>
    </#if>
    <ul>
        <#list items as item>
            <li class="list-item">
                <a href="<@slyak.query url="${item.url}"/>"
                   <#if slyakRequestContext.isSameUrl(item.url)>class="active" </#if>>
                    <i class="fas ${item.class}"></i>
                    <span>${item.title}</span>
                </a>
            </li>
        </#list>
    </ul>
</div>
</#macro>

<#macro project title btnCreate={}>
    <#assign left>
    <div>
        <div class="text-center">
            <img src="<@slyak.query url="/images/default-avatar.svg"/>">
            <div class="sidebar-title">大数据基础</div>
        </div>
        <div class="mt-2">
            <@layout.list title="操作" items=[
            {'title':'创建分组','url':'/project/group','class':'fa-plus'},
            {'title':'下载离线安装包','url':'/project/download','class':'fa-download'}
            ]/>
    <@layout.list title="导航" items=[{'title':'分组列表','url':'/project/groups','class':'fa-boxes'}]/>
    <@layout.list items=[{'title':'配置','url':'/project/settings','class':'fa-cog'}]/>
        </div>
    </div>
    </#assign>
    <#assign right>
        <#nested />
    </#assign>
    <@layout.rightMain title=title left=left right=right btnCreate=btnCreate/>
</#macro>

<#macro group title btnCreate={}>
    <#assign left>
    <div>
        <div class="text-left mb-3">
            <img src="<@slyak.query url="/images/default-avatar.svg"/>" style="width: 48px;position: absolute">
            <div style="padding-left: 58px;padding-top: 4px;height: 45px;">
                <a href="">NameNode</a>
                <div>大数据基础</div>
            </div>
        </div>
        <div class="mt-2">
            <@layout.list title="操作" items=[
            {'title':'运行所有脚本','url':'/group','class':'fa-paper-plane'},
            {'title':'下载离线安装包','url':'/group','class':'fa-download'}
            ]/>
    <@layout.list title="导航" items=[
        {'title':'主机列表','url':'/group/hosts','class':'fa-desktop'},
        {'title':'依赖项目','url':'/group/dependencies','class':'fa-cubes'},
        {'title':'文件列表','url':'/group/files','class':'fa-file'},
        {'title':'变量列表','url':'/group/envs','class':'fa-subscript'},
        {'title':'初始化脚本','url':'/group/scripts','class':'fa-code'}
        ]/>
    <@layout.list items=[{'title':'配置','url':'/group/settings','class':'fa-cog'}]/>
        </div>
    </div>
    </#assign>
    <#assign right>
        <#nested />
    </#assign>
    <@layout.rightMain title=title left=left right=right btnCreate=btnCreate/>
</#macro>

<#macro detail title action>
    <@html>
    <div class="detail">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${title}</h5>
                <hr/>
                <form action="<@slyak.query url=action/>" method="post" autocomplete="off">
                    <input style="display:none" type="text" name="fakename">
                    <input style="display:none" type="password" name="fakepwd">
                    <#nested />
                </form>
                <hr/>
                <div class="text-right">
                    <button class="btn btn-lg btn-primary">保存</button>
                    <button class="btn btn-lg btn-link" onclick="history.back()">取消</button>
                </div>
            </div>
        </div>
    </div>
    </@html>
</#macro>