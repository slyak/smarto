<@layout.layout_admin title="操作系统" btnCreate={'title':'添加操作系统','modal':true,'url':'/admin/os'}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col" class="text-center">版本号列表</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>centos</td>
        <td class="text-center">
            7.0.1406 <br/>
            7.1.1503 <br/>
        </td>
        <td><@bootstrap.a href="/admin/os?id=1" title="编辑" modal=true showSubmit=true/><@slyakUI.a href="/script/env/delete">删除</@slyakUI.a></td>
    </tr>
    <tr>
        <td>centos</td>
        <td class="text-center">
            7.0.1406 <br/>
            7.1.1503 <br/>
        </td>
        <td><@slyakUI.a href="/script/env/delete">删除</@slyakUI.a></td>
    </tr>
    <tr>
        <td>centos</td>
        <td class="text-center">
            7.0.1406 <br/>
            7.1.1503 <br/>
        </td>
        <td><@slyakUI.a href="/script/env/delete">删除</@slyakUI.a></td>
    </tr>
    </tbody>
</table>
</@layout.layout_admin>