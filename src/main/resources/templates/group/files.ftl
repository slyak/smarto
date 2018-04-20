<@layout.group title="文件列表">
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">类型</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-link"></i><a href="<@slyak.query url="/file/download"/>">https://github.com/slyak/spring-data-jpa-extra/archive/master.zip</a></td>
        <td>URL</td>
    </tr>
    <tr>
        <td><i class="fas fa-building"></i><a href="<@slyak.query url="/file/download"/>">createrepo wget</a></td>
        <td>YUM</td>
    </tr>
    <tr>
        <td><i class="fas fa-file"></i><a href="<@slyak.query url="/file/download"/>">HDP</a></td>
        <td>普通文件</td>
    </tr>
    <tr>
        <td><i class="fas fa-file"></i><a href="<@slyak.query url="/file/download"/>">HDP-UTILS</a></td>
        <td>普通文件</td>
    </tr>
    </tbody>
</table>
</@layout.group>