<#-- @ftlvariable name="project" type="com.slyak.mirrors.domain.Project" -->
<#-- @ftlvariable name="roles" type="java.util.List<com.slyak.mirrors.domain.ProjectRole>" -->
<@layout.layout_project title='角色列表' btnCreate={"title":"创建角色","url":"/project/role?projectId=${project.id}", 'modal':true,'showSubmit':true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">描述</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list roles as role>
        <tr>
            <td><i class="fas fa-boxes"></i>${role.name}</td>
            <td>${role.description}</td>
            <td>
                <a href="<@slyak.query url="/project/role/hosts?id=${role.id}"/>">管理</a>
                <@slyakUI.a href="/project/role/delete?id=${role.id}">删除</@slyakUI.a>
            </td>
        </tr>
        <#else >
        <tr>
            <td colspan="3" class="text-center">暂无记录</td>
        </tr>
        </#list>
    </tbody>
</table>
</@layout.layout_project>