<#-- @ftlvariable name="group" type="com.slyak.mirrors.domain.Group" -->
<@layout.project title="主机列表" btnCreate={'title':'创建主机','url':'/project/group/host'}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">主机名</th>
        <th scope="col">描述</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-desktop"></i>NameNode1</td>
        <td>节点一</td>
        <td><a href="<@slyak.query url="/group/host"/>">编辑</a><a href="<@slyak.query url="/group/host"/>">停用</a><a href="<@slyak.query url='/group/delete'/>">删除</a></td>
    </tr>
    <tr>
        <td><i class="fas fa-desktop"></i>NameNode2</td>
        <td>节点二</td>
        <td><a href="<@slyak.query url="/group/host"/>">编辑</a><a href="<@slyak.query url="/group/host"/>">停用</a><a href="<@slyak.query url='/group/delete'/>">删除</a></td>
    </tr>
    <tr>
        <td><i class="fas fa-desktop"></i>NameNode3</td>
        <td>节点三</td>
        <td><a href="<@slyak.query url="/group/host"/>">编辑</a><a href="<@slyak.query url="/group/host"/>">停用</a><a href="<@slyak.query url='/group/delete'/>">删除</a></td>
    </tr>
    </tbody>
</table>
</@layout.project>