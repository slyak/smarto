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
        <td><@slyakUI.a href="http://192.168.10.2/CentOS" target="_blank">CentOS7优化</@slyakUI.a></td>
        <td><@slyakUI.a href="/project/hosts" target="_blank">CentOS基础</@slyakUI.a></td>
        <td>CentOS</td>
        <td>[7.0,7.4)</td>
        <td>
            <@slyakUI.a href="/project/groups" class="mr-3">使用帮助</@slyakUI.a>
            <@slyakUI.a href="/project/groups">下载资源</@slyakUI.a>
        </td>
    </tr>
    <tr>
        <td><@slyakUI.a href="http://192.168.10.2/CentOS" target="_blank">CentOS YUM离线镜像仓库</@slyakUI.a></td>
        <td><@slyakUI.a href="/project/hosts" target="_blank">CentOS基础</@slyakUI.a></td>
        <td>CentOS</td>
        <td>[7.0,7.4)</td>
        <td>
            <@slyakUI.a href="/project/groups" class="mr-3">使用帮助</@slyakUI.a>
            <@slyakUI.a href="/project/groups">下载资源</@slyakUI.a>
        </td>
    </tr>
    </tbody>
</table>
    <@slyakUI.pagination value=page/>
</#assign>

<#assign right>
    <@layout.downloadOS/>
</#assign>

<@layout.leftMain title="脚本" left=left right=right/>