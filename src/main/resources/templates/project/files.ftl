<@layout.project title='脚本列表' btnCreate={"title":"创建分组","url":"/project/group"}>
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
        <td><i class="fas fa-boxes"></i><a href="<@slyak.query url="/project/group/hosts"/>">所有主机</a></td>
        <td>所有主机</td>
    </tr>
    <tr>
        <td><i class="fas fa-boxes"></i><a href="<@slyak.query url="/project/group/hosts"/>">NameNode</a></td>
        <td>NameNode JobExecutor HiveManager</td>
    </tr>
    <tr>
        <td><i class="fas fa-boxes"></i><a href="<@slyak.query url="/project/group/hosts"/>">DataNode</a></td>
        <td>DataNode TaskExecutor HiveNode</td>
    </tr>
    <tr>
        <td><i class="fas fa-boxes"></i><a href="<@slyak.query url="/project/group/hosts"/>">EdgeNode</a></td>
        <td>Zookeeper BI ReportServer</td>
    </tr>
    </tbody>
</table>
</@layout.project>