<@layout.project title='脚本列表' btnCreate={"title":"选择脚本","url":"/project/script"}>
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
        <td><i class="fas fa-code"></i><@slyakUI.a href="/project/script">CentOS网络优化</@slyakUI.a></td>
        <td><@slyakUI.a href="/project/script/delete">排序</@slyakUI.a><@slyakUI.a href="/project/script/delete">删除</@slyakUI.a></td>
    </tr>
    <tr>
        <td><i class="fas fa-code"></i><@slyakUI.a href="/project/script">CentOS硬盘优化</@slyakUI.a></td>
        <td><@slyakUI.a href="/project/script/delete">排序</@slyakUI.a><@slyakUI.a href="/project/script/delete">删除</@slyakUI.a></td>
    </tr>
    <tr>
        <td><i class="fas fa-code"></i><@slyakUI.a href="/project/script">CentOS安全优化</@slyakUI.a></td>
        <td><@slyakUI.a href="/project/script/delete">排序</@slyakUI.a><@slyakUI.a href="/project/script/delete">删除</@slyakUI.a></td>
    </tr>
    </tbody>
</table>
</@layout.project>