<#-- @ftlvariable name="projectRole" type="com.slyak.mirrors.domain.ProjectRole" -->
<@layout.layout_project_role title="主机列表" btnCreate={'title':'选择主机','url':'/project/role/hostPicker?id=${projectRole.id}',"modal":true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">主机名</th>
        <th scope="col">描述</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-desktop"></i>NameNode1</td>
        <td>节点一</td>
    </tr>
    <tr>
        <td><i class="fas fa-desktop"></i>NameNode2</td>
        <td>节点二</td>
    </tr>
    <tr>
        <td><i class="fas fa-desktop"></i>NameNode3</td>
        <td>节点三</td>
    </tr>
    </tbody>
</table>
</@layout.layout_project_role>