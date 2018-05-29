<#-- @ftlvariable name="groupScripts" type="java.util.List<com.slyak.mirrors.domain.ProjectGroupScript>" -->
<@layout.layout_project_group title="脚本列表" btnCreate={'title':'管理脚本','modal':true,'url':'/script/scriptsPicker'}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">操作系统</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list groupScripts as groupScript>
            <#assign script = groupScript.script/>
        <tr>
            <td><i class="fas fa-code"></i>${script.name}</td>
            <td>${script.osName}:${script.osVersions?join(",")}</td>
            <td>
                <a href="<@slyak.query url="/project/group/file"/>">查看</a>
                <a href="<@slyak.query url='/project/group/file/delete'/>">删除</a>
                <a href="<@slyak.query url='/project/group/file/delete'/>">启用</a>
            </td>
        </tr>
        <#else >
        <tr>
            <td colspan="3" class="text-center">暂无记录</td>
        </tr>
        </#list>
    </tbody>
</table>
</@layout.layout_project_group>