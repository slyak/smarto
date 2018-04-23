<@layout.group title="文件列表" btnCreate={'title':'添加文件','url':'/group/file'}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">类型</th>
        <th scope="col">删除</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-link"></i>https://github.com/slyak/spring-data-jpa-extra/archive/master.zip</td>
        <td>URL</td>
        <td><a class="btn-link mr-3" href="<@slyak.query url="/group/file"/>">编辑</a><a class="btn-link" href="<@slyak.query url='/group/file/delete'/>">删除</a></td>
    </tr>
    <tr>
        <td><i class="fas fa-building"></i>createrepo wget</td>
        <td>YUM</td>
        <td><a class="btn-link mr-3" href="<@slyak.query url="/group/file"/>">编辑</a><a class="btn-link" href="<@slyak.query url='/group/file/delete'/>">删除</a></td>
    </tr>
    <tr>
        <td><i class="fas fa-file"></i>HDP</td>
        <td>普通文件</td>
        <td><a class="btn-link mr-3" href="<@slyak.query url="/group/file"/>">编辑</a><a class="btn-link" href="<@slyak.query url='/group/file/delete'/>">删除</a></td>
    </tr>
    <tr>
        <td><i class="fas fa-file"></i>HDP-UTILS</td>
        <td>普通文件</td>
        <td><a class="btn-link mr-3" href="<@slyak.query url="/group/file"/>">编辑</a><a class="btn-link" href="<@slyak.query url='/group/file/delete'/>">删除</a></td>
    </tr>
    </tbody>
</table>
</@layout.group>