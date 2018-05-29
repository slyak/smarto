<#-- @ftlvariable name="project" type="com.slyak.mirrors.domain.Project" -->
<#-- @ftlvariable name="groups" type="java.util.List<com.slyak.mirrors.domain.ProjectGroup>" -->
<@layout.layout_project title='分组列表' btnCreate={"title":"创建分组","url":"/project/group?projectId=${project.id}", 'modal':true,'showSubmit':true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">描述</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list groups as group>
        <tr>
            <td><i class="fas fa-boxes"></i>${group.name}</td>
            <td>${group.description}</td>
            <td>
                <a href="<@slyak.query url="/project/group/hosts?id=${group.id}"/>">管理</a>
                <@slyakUI.a href="/project/group/delete?id=${group.id}">删除</@slyakUI.a>
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