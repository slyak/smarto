<#-- @ftlvariable name="envs" type="java.util.List<com.slyak.mirrors.domain.ScriptEnv>" -->
<@layout.script title="变量列表" btnCreate={'title':'添加变量','url':'/script/env','modal':true,"showSubmit":true}>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">键</th>
        <th scope="col">描述</th>
        <th scope="col">默认值</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <#if envs?size gt 0>
        <#list envs as env>
            <tr>
                <td>${env.key}</td>
                <td>${env.description}</td>
                <td>${env.defValue}</td>
                <td><@slyakUI.a href="/script/env/delete">删除</@slyakUI.a></td>
            </tr>
        </#list>
        <#else >
        <#--<tr><td colspan="4" class="text-center">暂无记录</td></tr>-->
        <tr>
            <td>JAVA_HOME</td>
            <td>JAVA根路径</td>
            <td>/opt/jdk-1.8</td>
            <td><@slyakUI.a href="/script/env/delete">删除</@slyakUI.a></td>
        </tr>
        <tr>
            <td>MAVEN_HOME</td>
            <td>maven根路径</td>
            <td>/opt/maven</td>
            <td><@slyakUI.a href="/script/env/delete">删除</@slyakUI.a></td>
        </tr>
        <tr>
            <td>MYSQL_USER_NAME</td>
            <td>MYSQL默认用户名</td>
            <td>ROOT</td>
            <td><@slyakUI.a href="/script/env/delete">删除</@slyakUI.a></td>
        </tr>
        <tr>
            <td>MYSQL_USER_PWD</td>
            <td>MYSQL默认用户密码</td>
            <td>123456</td>
            <td><@slyakUI.a href="/script/env/delete">删除</@slyakUI.a></td>
        </tr>
    </#if>
    </tbody>
</table>
<div class="alert alert-primary" role="alert">
    <p><i class="fa fa-info-circle pr-1"></i>变量可以使用在脚本中，以${r'${变量名称}'}方式引用，依赖项目的变量也会继承下来，供您修改。默认系统变量如下：</p>
    1. <b>HOST_IP</b>   当前主机IP<br/>
    2. <b>HOST_NAME</b> 主机名
</div>
</@layout.script>