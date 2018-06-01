<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Project>" -->
<#assign left>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">描述</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list page.content as project>
        <tr>
            <td>${project.name}</td>
            <td>${project.description}</td>
            <td>
                <a href="<@slyak.query url="/project/groups?id=${project.id}"/>">管理</a>
                <@bootstrap.a href="/project/delete?id=${project.id}" title="删除" class="confirm ajax"/>
            </td>
        </tr>
        <#else >
        <tr>
            <td colspan="3" class="text-center">暂无记录</td>
        </tr>
        </#list>
    </tbody>
</table>
    <@slyakUI.pagination value=page/>
</#assign>

<#assign right>
    <@layout.downloadOS/>
</#assign>

<@layout.leftMain title='项目' left=left right=right btnCreate={'title':'创建项目','url':'/project'}/>