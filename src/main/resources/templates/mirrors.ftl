<#-- @ftlvariable name="mirrors" type="java.util.List<com.slyak.smarto.domain.Mirror>" -->
<#assign left>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">更新时间</th>
        <th scope="col">同步状态</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#list mirrors as mirror>
    <tr>
        <td><a href="http://192.168.10.2/CentOS" target="_blank">${mirror.name}</a></td>
        <td>${mirror.lastUpdate}</td>
        <td class="text-success">success</td>
        <td>
            <@bootstrap.a href="#" title="使用帮助" modal=true modalContent="${mirror.help}"/>
        </td>
    </tr>
    </#list>
    </tbody>
</table>
    <@slyakUI.pagination value=page/>
</#assign>
<#assign right>
    <@layout.downloadOS/>
</#assign>

<@layout.leftMain title="镜像" left=left right=right/>