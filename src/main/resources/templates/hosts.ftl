<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.smarto.domain.Host>" -->
<#assign left>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">机器名</th>
        <th scope="col">IP</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list page.content as host>
        <tr>
            <td>${host.name}</td>
            <td>${host.ip}</td>
            <td>
                <a href="<@slyak.query url='/host/test?id=${host.id}'/>" class="ajax"
                   data-cb="{'true':'连接成功!','false':'连接失败!'}">测试</a>
                <@slyakUI.a href="/host?id=${host.id}">编辑</@slyakUI.a>
                <@slyakUI.a href="/host/delete?id=${host.id}" class="confirm ajax">删除</@slyakUI.a>
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

<@layout.leftMain title='主机' left=left right=right btnCreate={'title':'创建主机','url':'/host'}/>