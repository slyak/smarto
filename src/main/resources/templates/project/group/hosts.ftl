<#-- @ftlvariable name="groupHosts" type="java.util.List<com.slyak.mirrors.domain.ProjectGroupHost>" -->
<@layout.layout_project_group title="主机列表" btnCreate={'title':'选择主机','url':'/project/group/hostsPicker?id=${projectGroup.id}',"modal":true,'showSubmit':true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th>名称</th>
        <th>IP</th>
        <th>操作系统</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <#list groupHosts as gh>
            <#assign host = gh.host/>
        <tr>
            <td><i class="fas fa-desktop"></i>${host.name}</td>
            <td>${host.ip}</td>
            <td>${host.osName}:${host.osVersion}</td>
            <td><@slyakUI.a href="/project/group/deleteHost?projectGroupId=${projectGroup.id}&hostId=${host.id}" class="ajax confirm">
                删除</@slyakUI.a></td>
        </tr>
        <#else >
        <tr>
            <td colspan="4" class="text-center">暂无记录</td>
        </tr>
        </#list>
    </tbody>
</table>
</@layout.layout_project_group>