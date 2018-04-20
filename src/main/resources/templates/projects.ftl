<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Project>" -->

<#assign left>
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
</#assign>

<#assign right>
<img src="<@slyak.query url="/images/install-os.png"/>"/>
<div class="text-center">
    <a class="btn btn-lg btn-primary mt-2">下载OS镜像</a>
</div>
</#assign>

<@layout.leftMain title='项目' left=left right=right btnCreate={'title':'创建新项目','url':'/project'}/>