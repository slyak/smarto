<#-- @ftlvariable name="slyakRequestContext" type="com.slyak.web.support.freemarker.SlyakRequestContext" -->
<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Group>" -->
<@layout.project title='主机分组' btnCreate={"title":"创建分组","url":"/project/group"}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">描述</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-boxes"></i>所有主机</td>
        <td>所有主机</td>
        <td><a href="<@slyak.query url="/group/hosts"/>" class="mr-2">编辑</a><a href="<@slyak.query url="/"/>">删除</a></td>
    </tr>
    <tr>
        <td><i class="fas fa-boxes"></i>NameNode</td>
        <td>NameNode JobExecutor HiveManager</td>
        <td><a href="<@slyak.query url="/group/hosts"/>" class="mr-2">编辑</a><a href="<@slyak.query url="/"/>">删除</a></td>
    </tr>
    <tr>
        <td><i class="fas fa-boxes"></i>DataNode</td>
        <td>DataNode TaskExecutor HiveNode</td>
        <td><a href="<@slyak.query url="/group/hosts"/>" class="mr-2">编辑</a><a href="<@slyak.query url="/"/>">删除</a></td>
    </tr>
    <tr>
        <td><i class="fas fa-boxes"></i>EdgeNode</td>
        <td>Zookeeper BI ReportServer</td>
        <td><a href="<@slyak.query url="/group/hosts"/>" class="mr-2">编辑</a><a href="<@slyak.query url="/"/>">删除</a></td>
    </tr>
    </tbody>
</table>
</@layout.project>