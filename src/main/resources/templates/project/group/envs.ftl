<@layout.group title="变量列表" btnCreate={'title':'添加变量','url':'/group/env'}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">键</th>
        <th scope="col">值</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td>JAVA_HOME</td>
        <td>/opt/jdk-1.8</td>
        <td><a class="btn-link pr-3" href="<@slyak.query url="/project/group/env"/>">编辑</a><a class="btn-link" href="">删除</a></td>
    </tr>
    <tr>
        <td>MAVEN_HOME</td>
        <td>/opt/maven</td>
        <td><a class="btn-link pr-3" href="<@slyak.query url="/project/group/env"/>">编辑</a><a class="btn-link" href="">删除</a></td>
    </tr>
    <tr>
        <td>MYSQL_USER_NAME</td>
        <td>SLYAK</td>
        <td><a class="btn-link pr-3" href="<@slyak.query url="/project/group/env"/>">编辑</a><a class="btn-link" href="">删除</a></td>
    </tr>
    <tr>
        <td>MYSQL_USER_PWD</td>
        <td>123456</td>
        <td><a class="btn-link pr-3" href="<@slyak.query url="/project/group/env"/>">编辑</a><a class="btn-link" href="">删除</a></td>
    </tr>
    </tbody>
</table>
<div class="alert alert-primary" role="alert">
    <p><i class="fa fa-info-circle pr-1"></i>变量可以使用在脚本中，以${r'${变量名称}'}方式引用，依赖项目的变量也会继承下来，供您修改。默认系统变量如下：</p>
    1. <b>HOST_IP</b>   当前主机IP<br/>
    2. <b>HOST_NAME</b> 主机名
</div>
</@layout.group>