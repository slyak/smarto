<#-- @ftlvariable name="script" type="com.slyak.mirrors.domain.Script" -->
<@layout.layout_script title="脚本内容">
    <div class="mb-2 text-right">
    <@bootstrap.a modal=true href="/admin/sysEnvs" title="<i class='fa fa-info-circle text-info pr-1'></i>系统内置模板参数[freemarker]"/>
    </div>
    <@slyakUI.form action="/script">
    <input type="hidden" name="id" value="${script.id}">
        <@bootstrap.textarea name="content" rows=10 id="contentArea">${script.content}</@bootstrap.textarea>
    <button type="submit" class="btn btn-lg btn-primary mt-3">提交</button>
    </@slyakUI.form>
    <@ace.cssAndJs/>
    <@ace.init cssSelector="#contentArea" mode="sh" theme="tomorrow"/>
</@layout.layout_script>