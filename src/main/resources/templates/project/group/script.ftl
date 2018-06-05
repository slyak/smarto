<#-- @ftlvariable name="groupScript" type="com.slyak.smarto.domain.ProjectGroupScript" -->
<#-- @ftlvariable name="hosts" type="java.util.List<com.slyak.smarto.domain.Host>" -->
<@layout.cleanHtml>
    <@slyakUI.form action="/project/group/script">
    <input type="hidden" name="id" value="${groupScript.id}">
    <table class="table table-borderless">
        <thead>
        <tr>
            <th>名称</th>
            <th>描述</th>
            <th>值</th>
        </tr>
        </thead>
        <tbody>
            <#list groupScript.script.envs as env>
            <tr>
                <td>${env.key}</td>
                <td>${env.description}</td>
                <#assign existValue = groupScript.envs['${env.key}']/>
                <td><@bootstrap.input name="envs['${env.key}']" value="${existValue?has_content?string(existValue,env.defValue)}"/></td>
            </tr>
            <#else >
            <tr>
                <td colspan="3" class="text-center">暂无记录</td>
            </tr>
            </#list>
        </tbody>
    </table>
    </@slyakUI.form>
</@layout.cleanHtml>