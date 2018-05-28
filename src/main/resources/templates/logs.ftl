<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Batch>" -->
<#assign left>
    <@bootstrap.keywordSearch id="scriptSearch"/>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">编号</th>
        <th scope="col">主机</th>
        <th scope="col">脚本列表</th>
        <th scope="col">开始时间</th>
        <th scope="col">结束时间</th>
        <th scope="col">脚本状态</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list page.content as batch>
            <#assign row = batch.hostIds?size/>
            <#list batch.hosts as host>
            <tr>
                <#if host_index == 0>
                    <td rowspan="${row}">${batch.id}</td>
                </#if>
                <td>${host.name}[${host.ip}]</td>
                <td>${batch.scripts?join(",")}</td>
                <#assign task = batch.tasks?api.get(host.id)/>
                <td rowspan="${row}">${task.startAt?number_to_date?string("MM/dd HH:mm:ss")}</td>
                <td rowspan="${row}">
                    <#if task.stopAt gt 0>
                        ${task.stopAt?number_to_date?string("MM/dd HH:mm:ss")}
                        <#else > --
                    </#if>
                </td>
                <td>
                    <#if task.status == 'RUNNING'>
                        <#assign badgeClass="badge-secondary"/>
                    <#elseif task.status == 'FAILED'>
                        <#assign badgeClass="badge-danger"/>
                    <#elseif task.status == 'SUCCESS'>
                        <#assign badgeClass="badge-success"/>
                    </#if>
                    <span class="badge ${badgeClass}">${task.status.title}</span>
                </td>
                <td><@bootstrap.a href="/log/detail?batchId=${batch.id}&hostId=${host.id}" title="查看日志" modal=true/></td>
            </tr>
            </#list>
        </#list>
    </tbody>
</table>
    <@slyakUI.pagination value=page/>

<script>

</script>

</#assign>

<#assign right>
    <@layout.downloadOS/>
</#assign>

<@layout.leftMain title='日志' left=left right=right/>
