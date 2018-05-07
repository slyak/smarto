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
    <#--<#list page.content as group>-->
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
    </tbody>
</table>
</@layout.script>