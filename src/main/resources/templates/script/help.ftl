<#-- @ftlvariable name="script" type="com.slyak.smarto.domain.Script" -->
<@layout.layout_script title="使用帮助">
    <@slyakUI.form action="/script">
    <input type="hidden" name="id" value="${script.id}">
        <@bootstrap.textarea name="help" rows=10 placeHolder="请输入一些使用帮助，让小伙伴们方便使用">${script.help}</@bootstrap.textarea>
    <button type="submit" class="btn btn-lg btn-primary mt-3">提交</button>
    </@slyakUI.form>
</@layout.layout_script>