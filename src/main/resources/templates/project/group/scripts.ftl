<@layout.group title="脚本列表" btnCreate={'title':'运行脚本','url':'javascript:runscripts(${groupHost.id})'}>
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
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-code"></i>CentOS优化</td>
        <td>CentOS7</td>
        <td>[7.0,7.4]</td>
        <td>
            <a href="<@slyak.query url="/project/group/file"/>">查看</a>
            <a href="<@slyak.query url='/project/group/file/delete'/>">删除</a>
            <a href="<@slyak.query url='/project/group/file/delete'/>">启用</a>
        </td>
    </tr>
    <tr>
        <td><i class="fas fa-code"></i>AmbariManager安装</td>
        <td>CentOS7</td>
        <td>[7.0,7.4]</td>
        <td>
            <a href="<@slyak.query url="/project/group/file"/>">查看</a>
            <a href="<@slyak.query url='/project/group/file/delete'/>">删除</a>
            <a href="<@slyak.query url='/project/group/file/delete'/>">启用</a>
        </td>
    </tr>
    </tbody>
</table>
</@layout.group>
