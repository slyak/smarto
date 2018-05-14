<#-- @ftlvariable name="files" type="java.util.List<com.slyak.mirrors.domain.ScriptFile>" -->
<@layout.layout_script title="文件列表" btnCreate={'title':"上传文件",'url':'/script/file?scriptId=${RequestParameters.id}','modal':true,'showSubmit':true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">文件名</th>
        <th scope="col">目标文件夹</th>
        <th scope="col">描述</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#if files?size gt 0>
            <#list files as f>
            <tr>
                <td><i class="fas fa-file"></i>${f.globalFile.name}</td>
                <td>${f.scpPath}</td>
                <td>${f.description}</td>
                <td><@bootstrap.a modal=true showSubmit=true href="/script/file?id=${f.id}" title="编辑"/><@slyakUI.a href="/script/file/delete?id=${f.id}">
                    删除</@slyakUI.a></td>
            </tr>
            </#list>
        <#else >
        <tr>
            <td colspan="4" class="text-center">暂无记录</td>
        </tr>
        </#if>
    </tbody>
</table>
</@layout.layout_script>