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
    {'title':'镜像仓库','url':'/mirror/mirrors'}
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

<#macro leftMain left right title="" btnCreate={}>
    <@html>
    <div class="layout-l-r bg-white">
        <div class="layout-cell left">
            <#if title?has_content>
                <@titleLine title=title btnCreate=btnCreate/>
            </#if>
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
            {'title':'添加主机','url':'/project/host','class':'fa-plus'},
            {'title':'创建分组','url':'/project/group','class':'fa-plus'},
            {'title':'下载离线安装包','url':'/project/download','class':'fa-download'}
            ]/>
    <@layout.list title="导航" items=[
        {'title':'主机列表','url':'/project/hosts','class':'fa-boxes'},
        {'title':'主机分组','url':'/project/groups','class':'fa-boxes'},
        {'title':'脚本与文件','url':'/project/scripts','class':'fa-boxes'}
        ]/>
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
            {'title':'编辑初始化脚本','url':'/group/script','class':'fa-upload'},
            {'title':'下载离线安装包','url':'/group/downloadPkg','class':'fa-download'}
            ]/>
    <@layout.list title="导航" items=[
        {'title':'主机列表','url':'/group/hosts','class':'fa-desktop'},
        {'title':'依赖列表','url':'/group/dependencies','class':'fa-cubes'},
        {'title':'文件列表','url':'/group/files','class':'fa-file'},
        {'title':'变量列表','url':'/group/envs','class':'fa-subscript'},
        {'title':'主机分组','url':'/group/scripts','class':'fa-code'},
        {'title':'历史版本','url':'/group/history','class':'fa-code-branch'}
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

<#macro admin title btnCreate={}>
    <#assign left>
    <div>
        <h4>系统管理</h4>
        <div class="mt-2">
            <@layout.list items=[
            {'title':'全局设置','url':'/admin','class':'fa-globe'}
            ]/>
        </div>
        <@layout.list title="用户" items=[
        {'title':'用户管理','url':'/admin/users','class':'fa-user'},
        {'title':'角色管理','url':'/admin/roles','class':'fa-user-secret'}
        ]/>
        <@layout.list title="文件" items=[
        {'title':'镜像仓库','url':'/admin/mirrors','class':'fa-warehouse'},
        {'title':'操作系统','url':'/admin/os','class':'fa-linux'}
        ]/>
        <@layout.list title="备份" items=[
        {'title':'系统备份','url':'/admin/backup','class':'fa-archive'},
        {'title':'系统恢复','url':'/admin/recover','class':'fa-recycle'}
        ]/>
    </div>
    </#assign>
    <#assign right>
    <div class="settings">
        <#nested />
    </div>
    </#assign>
    <@layout.rightMain title=title left=left right=right btnCreate=btnCreate/>
</#macro>

<#macro settings title btnCreate={}>
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
        {'title':'依赖列表','url':'/group/dependencies','class':'fa-cubes'},
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

<#macro detail title action enctype="application/x-www-form-urlencoded">
    <@html>
    <div class="detail">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${title}</h5>
                <hr/>
                <form action="<@slyak.query url=action/>" method="post" autocomplete="off" enctype="${enctype}">
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