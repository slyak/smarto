<@layout.group title="依赖项目" btnCreate={'title':'添加依赖','url':'/group/dependency'}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">项目名称</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td>JAVA_HOME</td>
        <td><a class="btn-link" href="">删除</a></td>
    </tr>
    <tr>
        <td>MYSQL_USER_PWD</td>
        <td><a class="btn-link" href="">删除</a></td>
    </tr>
    </tbody>
</table>
</@layout.group>