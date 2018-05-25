<#-- @ftlvariable name="script" type="com.slyak.mirrors.domain.Script" -->
<@layout.layout_script title="变量列表" btnCreate={'title':'保存','url':'javascript:saveEnvs()'}>
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
                <td>${env.key}<input type="hidden" name="envs[${env_index}].key" value="${env.key}"></td>
                <td><@bootstrap.input name="envs[${env_index}].description" value="${env.description}"/></td>
                <td><@bootstrap.input name="envs[${env_index}].defValue" value="${env.defValue}"/></td>
                <td>asd</td>
            </tr>
            <#else >
            </#list>
        </tbody>
    </table>
    </@slyakUI.form>
<div class="alert alert-primary" role="alert">
    <i class="fa fa-info-circle pr-1"></i>上述变量均来自脚本中形如 ： <b class="text-success">$变量名称</b>或<b
        class="text-success">${r'${变量名称}'}</b>的变量，请补全描述信息和默认值。
</div>
<script>
    function saveEnvs() {
        $("#envsForm").submit();
    }
</script>

</@layout.layout_script>