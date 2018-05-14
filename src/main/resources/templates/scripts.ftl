<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Script>" -->
<#assign left>
    <@bootstrap.keywordSearch id="scriptSearch"/>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">操作系统</th>
        <th scope="col">版本范围</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list page.content as sc>
        <tr>
            <td><@slyakUI.a href="/script/files?id=${sc.id}">${sc.name}</@slyakUI.a></td>
            <td>${sc.osName}</td>
            <td><#if sc.osVersions?has_content>${sc.osVersions?join(",")}</#if></td>
            <td>
                <@bootstrap.a href="#" title="使用帮助" class="mr-3" modal=true modalContent="${sc.help}"/>
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
</#assign>

<#assign right>
    <@layout.downloadOS/>
</#assign>

<@layout.leftMain title="脚本" left=left right=right btnCreate={'title':'创建脚本','url':'/script'}/>