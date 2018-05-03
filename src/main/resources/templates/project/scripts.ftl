<@layout.project title='脚本与文件' btnCreate={"title":"创建分组","url":"/project/group"}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td>
            <div><i class="fas fa-code"></i>CentOS优化</div>
        </td>
        <td>
            <a class="btn-link mr-3" href="<@slyak.query url="/project/group/file"/>">查看</a>
            <a class="btn-link mr-3" href="<@slyak.query url="/project/group/file"/>">运行</a>
        </td>
    </tr>
    <tr>
        <td><i class="fas fa-code"></i>AmbariManager安装</td>
        <td>
            <a class="btn-link mr-3" href="<@slyak.query url="/project/group/file"/>">查看</a>
            <a class="btn-link mr-3" href="<@slyak.query url="/project/group/file"/>">运行</a>
        </td>
    </tr>
    </tbody>
</table>
</@layout.project>