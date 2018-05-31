<#-- @ftlvariable name="projectGroup" type="com.slyak.mirrors.domain.ProjectGroup" -->
<#-- @ftlvariable name="project" type="com.slyak.mirrors.domain.Project" -->
<#-- @ftlvariable name="script" type="com.slyak.mirrors.domain.Script" -->
<#-- @ftlvariable name="slyakRequestContext" type="com.slyak.web.support.freemarker.SlyakRequestContext" -->
<#macro cleanHtml>
<html>
<head>
    <@bootstrap.cssAndJs/>
    <@slyak.js url="/webjars/jquery-migrate/jquery-migrate.min.js"/>
    <@slyakUI.cssAndJs/>
    <@slyakUI.fontasome/>
    <@slyak.css url="/css/main.css"/>
    <@slyak.js url="/js/main.js"/>
</head>
<body class="clean-html<#if RequestParameters.safe??> safe</#if>">
    <#nested />
</body>
</html>
</#macro>

<#macro html>
    <@cleanHtml>
        <@bootstrap.navbar brand="ITASM" left=[
        {'title':'脚本','url':'/scripts'},
        {'title':'日志','url':'/logs'},
        {'title':'主机','url':'/hosts'},
        {'title':'项目','url':'/projects'},
        {'title':'镜像','url':'/mirrors'}
        ] right=[
        {'title':'<i class="fa fa-question-circle fa-lg"></i>','url':'/help'},
        {'title':'<i class="fa fa-cog fa-lg"></i>','url':'/admin/index'}
        ]/>
    <div class="container-fluid p-0">
        <#nested />
    </div>
    </@cleanHtml>
</#macro>

<#macro footer>
<div class="footer">
    Slyak ITASM v0.1.0&nbsp;&nbsp;&nbsp;&nbsp;stormning@163.com&nbsp;&nbsp;&nbsp;&nbsp;slyak.com
    <div class="logo">Slyak</div>
</div>
</#macro>

<#macro titleLine title btnCreate={}>
    <#if title?has_content>
    <div class="title-line">
        <div class="title-line-main">
            <h1>${title}</h1>
            <#if btnCreate.title??>
                <#if btnCreate.modal??>
                    <@bootstrap.a href="${btnCreate.url}" title=btnCreate.title modal=true showSubmit=btnCreate.showSubmit!false class="btn btn-sm ml-3"/>
                <#else >
                    <a class="btn btn-sm ml-3" href="<@slyak.query url="${btnCreate.url}"/>">${btnCreate.title}</a>
                </#if>
            </#if>
        </div>
    </div>
    </#if>
</#macro>

<#macro main title="" btnCreate={}>
    <@html>
        <@titleLine title=title btnCreate=btnCreate/>
    <div class="main">
        <#nested />
    </div>
    </@html>
</#macro>

<#macro rightMain left right title="" btnCreate={}>
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
                <a class="<#if slyakRequestContext.isSameUrl(item.url)>active</#if><@slyak.addClass values=[item.aClass]/>"
                   href="<@slyak.query url="${item.url}" extra=RequestParameters/>">
                    <i class="fas ${item.class}"></i>
                    <span>${item.title}</span>
                </a>
            </li>
        </#list>
    </ul>
</div>
</#macro>

<#macro downloadOS>
<img src="<@slyak.query url="/images/install-os.png"/>"/>
<div class="text-center">
    <a class="btn btn-lg btn-primary mt-2">下载OS镜像</a>
</div>
</#macro>

<#macro layout_project title btnCreate={}>
    <#assign left>
    <div>
        <div class="text-center fa-">
            <img src="<@slyak.query url="/images/default-avatar.svg"/>">
            <div class="sidebar-title">${project.name}</div>
        </div>
        <div class="mt-2">
            <@layout.list title="操作" items=[
            {'title':'运行项目脚本','url':'/project/host','class':'fa-paper-plane'},
            {'title':'下载离线安装包','url':'/project/download','class':'fa-download'}
            ]/>
    <@layout.list title="导航" items=[
        {'title':'分组列表','url':'/project/groups','class':'fa-boxes'}
        ]/>
    <@layout.list items=[{'title':'配置','url':'/project','class':'fa-cog'}]/>
        </div>
    </div>
    </#assign>
    <#assign right>
        <#nested />
    </#assign>
    <@layout.rightMain title=title left=left right=right btnCreate=btnCreate/>
</#macro>

<#macro layout_project_group title btnCreate={}>
    <#assign left>
    <div>
        <div class="text-left mb-3">
            <img src="<@slyak.query url="/images/default-avatar.svg"/>" style="width: 48px;position: absolute">
            <div style="padding-left: 58px;padding-top: 4px;height: 45px;">
                <a href="<@slyak.query "/project/groups?id=${projectGroup.project.id}"/>">${projectGroup.project.name}</a>
                <div>${projectGroup.name}</div>
            </div>
        </div>
        <div class="mt-2">
            <@layout.list title="操作" items=[
            {'title':'运行脚本','url':'/project/group/script','class':'fa-paper-plane'}
            ]/>
    <@layout.list title="导航" items=[
        {'title':'脚本列表','url':'/project/group/scripts','class':'fa-code'},
        {'title':'主机列表','url':'/project/group/hosts','class':'fa-desktop'},
        {'title':'运行日志','url':'/logs','class':'fa-terminal'}
        ]/>
    <@layout.list items=[{'title':'配置','url':'/project/group','class':'fa-cog'}]/>
        </div>
    </div>
    </#assign>
    <#assign right>
        <#nested />
    </#assign>
    <@layout.rightMain title=title left=left right=right btnCreate=btnCreate/>
</#macro>

<#macro layout_admin title btnCreate={}>
    <#assign left>
    <div>
        <h4>系统管理</h4>
        <div class="mt-2">
            <@layout.list items=[
            {'title':'全局设置','url':'/admin/index','class':'fa-globe'}
            ]/>
        </div>
        <@layout.list title="用户" items=[
        {'title':'用户管理','url':'/admin/users','class':'fa-user'},
        {'title':'分组管理','url':'/admin/groups','class':'fa-user-secret'}
        ]/>
        <@layout.list title="文件" items=[
        {'title':'镜像','url':'/admin/mirrors','class':'fa-warehouse'},
        {'title':'操作系统','url':'/admin/oss','class':'fa-hdd'}
        ]/>
        <@layout.list title="备份" items=[
        {'title':'系统备份','url':'/admin/backup','class':'fa-archive'},
        {'title':'系统恢复','url':'/admin/recover','class':'fa-recycle'}
        ]/>
    </div>
    </#assign>
    <#assign right>
        <#nested />
    </#assign>
    <@layout.rightMain title=title left=left right=right btnCreate=btnCreate/>
</#macro>

<#macro layout_script title btnCreate={}>
    <#assign left>
    <div>
        <div class="text-center fa-">
            <img src="<@slyak.query url="/images/default-avatar.svg"/>">
            <div class="sidebar-title">${script.name}</div>
        </div>
        <div class="mt-2">
            <@layout.list title="操作" items=[
            {'title':'运行脚本','url':'/script/run','class':'fa-paper-plane','aClass':'run-script'},
            {'title':'查看日志','url':'/logs','class':'fa-terminal'}
            ]/>
    <@layout.list title="导航" items=[
        {'title':'脚本内容','url':'/script/content','class':'fa-tasks'},
        {'title':'文件列表','url':'/script/files','class':'fa-file'},
        {'title':'变量列表','url':'/script/envs','class':'fa-subscript'},
        {'title':'使用帮助','url':'/script/help','class':'fa-question-circle'}
        ]/>
    <@layout.list items=[{'title':'配置','url':'/script','class':'fa-cog'}]/>
        </div>
    </div>
    </#assign>
    <#assign right>
        <#nested />
    </#assign>
    <@layout.rightMain title=title left=left right=right btnCreate=btnCreate/>
</#macro>

<#macro cardForm title action enctype="application/x-www-form-urlencoded">
<div class="detail">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">${title}</h5>
            <hr/>
            <@slyakUI.form action=action enctype=enctype>
                <#nested />
                <hr/>
                <div class="text-right">
                    <button class="btn btn-lg btn-primary" type="submit">保存</button>
                    <button class="btn btn-lg btn-link" type="button" onclick="history.back()">取消</button>
                </div>
            </@slyakUI.form>
        </div>
    </div>
</div>
</#macro>

<#macro layout_detail title action enctype="application/x-www-form-urlencoded">
    <@html>
        <@cardForm title=title action=action enctype=enctype>
            <#nested />
        </@cardForm>
    </@html>
</#macro>