<#-- @ftlvariable name="mirrors" type="java.util.List<com.slyak.smarto.domain.Mirror>" -->
<@layout.layout_admin title="镜像" btnCreate={'title':'添加镜像','modal':true,'url':'/admin/mirror','showSubmit':true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">所属主机</th>
        <th scope="col">同步时间</th>
        <th scope="col">同步状态</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list mirrors as mirror>
        <tr>
            <td><a href="${mirror.baseUrl}" target="_blank">${mirror.name}</a></td>
            <td>${mirror.host.name}-${mirror.host.ip}</td>
            <td>${mirror.lastUpdate}</td>
            <td class="text-success">success</td>
            <td>
                <@bootstrap.a href="/admin/mirror?id=${mirror.id}" title="管理" modal=true showSubmit=true/>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@layout.layout_admin>