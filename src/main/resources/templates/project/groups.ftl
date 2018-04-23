<#-- @ftlvariable name="slyakRequestContext" type="com.slyak.web.support.freemarker.SlyakRequestContext" -->
<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Group>" -->
<#assign right>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">描述</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-boxes"></i><a href="<@slyak.query url="/group/hosts"/>">NameNode</a></td>
        <td>NameNode JobExecutor HiveManager</td>
    </tr>
    <tr>
        <td><i class="fas fa-boxes"></i><a href="<@slyak.query url="/group/hosts"/>">DataNode</a></td>
        <td>DataNode TaskExecutor HiveNode</td>
    </tr>
    <tr>
        <td><i class="fas fa-boxes"></i><a href="<@slyak.query url="/group/hosts"/>">EdgeNode</a></td>
        <td>Zookeeper BI ReportServer</td>
    </tr>
    </tbody>
</table>
</#assign>

<#assign left>
<div>
    <div class="text-center">
        <img src="<@slyak.query url="/images/default-avatar.svg"/>">
        <div class="sidebar-title">大数据基础</div>
    </div>
    <div class="mt-2">
        <@layout.list title="操作" items=[
        {'title':'创建分组','url':'/group','class':'fa-plus'},
        {'title':'下载离线安装包','url':'/project/download','class':'fa-download'}
        ]/>
    <@layout.list title="导航" items=[{'title':'分组列表','url':'/project/groups','class':'fa-boxes'}]/>
    <@layout.list items=[{'title':'配置','url':'/project/settings','class':'fa-cog'}]/>
    </div>
</div>
</#assign>

<@layout.rightMain title='分组列表' left=left right=right/>