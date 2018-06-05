<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.smarto.domain.Script>" -->
<#assign left>
    <@bootstrap.keywordSearch id="scriptSearch"/>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">操作系统</th>
        <th scope="col">版本范围</th>
        <th scope="col" class="text-center">最新状态</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list page.content as sc>
        <tr>
            <td>${sc.name}</td>
            <td>${sc.osName}</td>
            <td><#if sc.osVersions?has_content>${sc.osVersions?join(",")}</#if></td>
            <td class="text-center">
                <#if sc.latestStatus == 'FAILED'>
                    <#assign badgeClass="badge-danger"/>
                <#elseif sc.latestStatus == 'SUCCESS'>
                    <#assign badgeClass="badge-success"/>
                <#else>
                    <#assign badgeClass="badge-secondary"/>
                </#if>
                <span class="badge ${badgeClass}">${sc.latestStatus.title}</span>
            </td>
            <td>
                <@bootstrap.a href="#" title="使用帮助" modal=true modalContent="${sc.help}"/>
                <@slyakUI.a href="/script/content?id=${sc.id}">管理</@slyakUI.a>
                <a href="<@slyak.query url="/script/delete?id=${sc.id}"/>" class="confirm ajax"
                   data-cb="mentionDelete">删除</a>
            </td>
        </tr>
        <#else >
        <tr>
            <td colspan="4" class="text-center">暂无数据</td>
        </tr>
        </#list>
    </tbody>
</table>
    <@slyakUI.pagination value=page/>
<script>
    function mentionDelete(ret) {
        if (ret) {
            location.reload();
        } else {
            alert("已经有项目在使用此脚本，无法删除！")
        }
    }
</script>
</#assign>

<#assign right>
    <@layout.downloadOS/>
</#assign>

<@layout.leftMain title="脚本" left=left right=right btnCreate={'title':'创建脚本','url':'/script'}/>