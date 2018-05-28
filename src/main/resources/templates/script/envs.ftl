<#-- @ftlvariable name="script" type="com.slyak.mirrors.domain.Script" -->
<@layout.layout_script title="变量列表" btnCreate={'title':'添加变量','url':'/script/env?scriptId=${script.id}','modal':true,'showSubmit':true}>
    <@slyakUI.form action="/script/envs" id="envsForm">
    <input type="hidden" name="id" value="${script.id}"/>
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
            <#list script.envs as env>
            <tr>
                <td>${env.key}</td>
                <td>${env.description}</td>
                <td>${env.defValue}</td>
                <td>
                    <@bootstrap.a href="/script/env?scriptId=${script.id}&key=${env.key}" title="编辑变量" modal="true" showSubmit=true/>
                    <@slyakUI.a href="/script/env/delete?scriptId=${script.id}&key=${env.key}" class="confirm ajax">
                        删除</@slyakUI.a>
                </td>
            </tr>
            <#else >
            </#list>
        </tbody>
    </table>
    </@slyakUI.form>
<div class="alert alert-primary" role="alert">
    <i class="fa fa-info-circle pr-1"></i>上述变量和自脚本中形如 ： <b class="text-success">${r'${变量名称}'}</b>的变量一一对应，请补全描述信息和默认值。
</div>

</@layout.layout_script>