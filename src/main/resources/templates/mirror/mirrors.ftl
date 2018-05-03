<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Project>" -->
<#assign left>
    <@bootstrap.keywordSearch id="mirrorSearch"/>
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
    <tr>
        <td><a href="http://192.168.10.2/CentOS" target="_blank">CentOS</a></td>
        <td>2018-04-27 10:30:00</td>
        <td class="text-success">success</td>
        <td><a href="<@slyak.query url="/project/groups"/>">使用帮助</a></td>
    </tr>
    <tr>
        <td><a href="http://192.168.10.2/HDP" target="_blank">HDP</a></td>
        <td>2018-04-27 10:30:00</td>
        <td class="text-success">success</td>
        <td><a href="<@slyak.query url="/project/groups"/>">使用帮助</a></td>
    </tr>
    <tr>
        <td><a href="http://192.168.10.2/HDP-UTILS-1.1.0.20/" target="_blank">HDP-UTILS</a></td>
        <td>2018-04-27 10:30:00</td>
        <td class="text-success">success</td>
        <td><a href="<@slyak.query url="/project/groups"/>">使用帮助</a></td>
    </tr>
    </tbody>
</table>
    <@slyakUI.pagination value=page/>
</#assign>

<#assign right>
<@layout.downloadOS/>
</#assign>

<@layout.leftMain title="镜像" left=left right=right/>