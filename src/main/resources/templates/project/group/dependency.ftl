<@layout.detail title="添加依赖" action="/projects">
<@bootstrap.keywordSearch id="mirrorSearch"/>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col"><input type="checkbox"></th>
        <th scope="col">依赖组</th>
        <th scope="col">隶属项目</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><input type="checkbox"></td>
        <td>CentOS操作系统优化</td>
        <td>CentOS基础</td>
    </tr>
    <tr>
        <td><input type="checkbox"></td>
        <td>CentOS镜像仓库变更</td>
        <td>CentOS基础</td>
    </tr>
    <tr>
        <td><input type="checkbox"></td>
        <td>CentOS Docker安装</td>
        <td>CentOS基础</td>
    </tr>
    </tbody>
</table>
</@layout.detail>