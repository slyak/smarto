<#-- @ftlvariable name="script" type="com.slyak.mirrors.domain.Script" -->
<@layout.layout_script title="配置" btnCreate={'title':'删除','url':'/project/delete'}>
<div class="settings">
    <@slyakUI.form action="/script">
        <input type="hidden" name="id" value="${script.id}">
        <@bootstrap.formgroup label="名称" required=true>
            <@bootstrap.input type="text" name="name" value="${script.name}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="内容" required=true >
            <@bootstrap.textarea name="content">${script.content}</@bootstrap.textarea>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="">
            <button type="submit" class="btn btn-lg btn-primary">提交</button>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</div>
</@layout.layout_script>