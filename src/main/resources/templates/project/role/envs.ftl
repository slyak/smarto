<@layout.layout_project_role title="变量列表" btnCreate={'title':'保存配置','url':'/role/env'}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">键</th>
        <th scope="col">描述</th>
        <th scope="col">值</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td>JAVA_HOME</td>
        <td>JAVA根路径</td>
        <td><@bootstrap.input name="value" value="/opt/jdk-1.8"/></td>
    </tr>
    <tr>
        <td>MAVEN_HOME</td>
        <td>maven根路径</td>
        <td><@bootstrap.input name="value" value="/opt/maven"/></td>
    </tr>
    <tr>
        <td>MYSQL_USER_NAME</td>
        <td>MYSQL默认用户名</td>
        <td><@bootstrap.input name="value" value="SLYAK"/></td>
    </tr>
    <tr>
        <td>MYSQL_USER_PWD</td>
        <td>MYSQL默认用户密码</td>
        <td><@bootstrap.input name="value" value="123456"/></td>
    </tr>
    </tbody>
</table>
<div class="alert alert-primary" role="alert">
    <p><i class="fa fa-info-circle pr-1"></i>变量可以使用在脚本中，以${r'${变量名称}'}方式引用，依赖项目的变量也会继承下来，供您修改。默认系统变量如下：</p>
    1. <b>HOST_IP</b>   当前主机IP<br/>
    2. <b>HOST_NAME</b> 主机名
</div>
</@layout.layout_project_role>