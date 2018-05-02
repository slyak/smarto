<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Project>" -->
<#assign left>
    <@bootstrap.keywordSearch id="scriptSearch"/>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">来源项目</th>
        <th scope="col">操作系统</th>
        <th scope="col">版本范围</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><a href="http://192.168.10.2/CentOS" target="_blank">CentOS7优化</a></td>
        <td><a href="<@slyak.query url='/project/hosts'/>">CentOS基础</a></td>
        <td>CentOS</td>
        <td>[7.0,7.4)</td>
        <td>
            <a class="mr-3" href="<@slyak.query url="/project/groups"/>">使用帮助</a>
            <a href="<@slyak.query url="/project/groups"/>">下载资源</a>
        </td>
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

<@layout.leftMain title="脚本仓库" left=left right=right/>