<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Project>" -->
<@layout.main title='项目' btnCreate={'title':'创建新项目','url':'/project'}>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">父项目</th>
        <th scope="col">描述</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><a href="<@slyak.query url="/project"/>">大数据基础</a></td>
        <td></td>
        <td>基于Ambari PHD的大数据基础环境</td>
    </tr>
    <tr>
        <td><a href="<@slyak.query url="/project"/>">大数据日志审计</a></td>
        <td><a href="<@slyak.query url="/project"/>">大数据基础</a></td>
        <td>大数据日志审计基础环境</td>
    </tr>
    </tbody>
</table>
    <@bootstrap.pagination value=page relativeUrl="/" />
</@layout.main>