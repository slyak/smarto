<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Project>" -->
<@layout.main title='项目' btnCreate={'title':'创建新项目','url':'/project'}>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">名称</th>
        <th scope="col">父项目</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as project>-->
        <#list 1..3 as project>
        <tr>
        <#--<td>${project.id}</td>
        <td>${project.name}</td>
        <td>${project.parent.name}</td>-->
            <td>1</td>
            <td>test</td>
            <td>test</td>
            <td>
                <a class="btn-link" href="<@slyak.query url="/project"/>">详情</a>
                <a class="btn-link" href="<@slyak.query url="/export"/>">导出</a>
                <a class="btn-link" href="<@slyak.query url="/install"/>">离线安装</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
<@bootstrap.pagination value=page relativeUrl="/" />
</@layout.main>