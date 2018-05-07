<#-- @ftlvariable name="files" type="java.util.List<com.slyak.mirrors.domain.ScriptFile>" -->
<@layout.script title="文件列表" btnCreate={'title':"上传文件",'url':'/script/file','modal':true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">文件名</th>
        <th scope="col">存储路径</th>
        <th scope="col">描述</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
        <#if files?size gt 0>
            <#list files as f>
            <tr>
                <td><i class="fas fa-file"></i>${f.name}</td>
                <td>${f.scpPath}</td>
                <td>${f.description}</td>
                <td><@slyakUI.a href="">删除</@slyakUI.a></td>
            </tr>
            </#list>
            <#else >
            <#--<tr><td colspan="4" class="text-center">暂无记录</td></tr>-->
            <tr>
                <td><i class="fas fa-file"></i>tomcat7.tar.gz</td>
                <td>/opt/tomcat7.tar.gz</td>
                <td>tomcat7安装包</td>
                <td><@slyakUI.a href="">删除</@slyakUI.a></td>
            </tr>
            <tr>
                <td><i class="fas fa-file"></i>mysql5.tar.gz</td>
                <td>/opt/mysql5.tar.gz</td>
                <td>mysql5安装包</td>
                <td><@slyakUI.a href="">删除</@slyakUI.a></td>
            </tr>
        </#if>
    </tbody>
</table>
</@layout.script>