<#-- @ftlvariable name="group" type="com.slyak.mirrors.domain.Group" -->
<@layout.group title="主机列表" btnCreate={'title':'创建主机','url':'/group/host'}>
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
        <td><i class="fas fa-desktop"></i><a href="<@slyak.query url="/group/host"/>">NameNode1</a></td>
        <td>节点一</td>
    </tr>
    <tr>
        <td><i class="fas fa-desktop"></i><a href="<@slyak.query url="/group/host"/>">NameNode2</a></td>
        <td>节点二</td>
    </tr>
    <tr>
        <td><i class="fas fa-desktop"></i><a href="<@slyak.query url="/group/host"/>">NameNode3</a></td>
        <td>节点三</td>
    </tr>
    </tbody>
</table>
</@layout.group>