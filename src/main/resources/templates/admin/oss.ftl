<#-- @ftlvariable name="oss" type="java.util.List<com.slyak.mirrors.domain.OS>" -->
<@layout.layout_admin title="操作系统" btnCreate={'title':'添加操作系统','modal':true,'url':'/admin/os','showSubmit':true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col" class="text-center">版本号列表</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#list oss as os>
        <tr>
            <td>${os.os}</td>
            <td class="text-center">
                <#list os.versions as v>
                    <div>${v}</div>
                </#list>
            </td>
            <td>
                <@bootstrap.a href="/admin/os?id=${os.id}" title="编辑" modal=true showSubmit=true/>
                <@slyakUI.a href="/admin/os/delete?id=${os.id}">删除</@slyakUI.a>
            </td>
        </tr>
        <#else >
        <tr>
            <td colspan="3" class="text-center">暂无记录</td>
        </tr>
        </#list>
    </tbody>
</table>
</@layout.layout_admin>