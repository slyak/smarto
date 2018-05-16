<#-- @ftlvariable name="senv" type="com.slyak.mirrors.domain.ScriptEnv" -->
<@layout.cleanHtml>
    <@slyakUI.form action="/script/env">
        <@bootstrap.formgroup label="键" required=true>
            <@bootstrap.input name="key" value="${senv.key}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="描述" required=true>
            <@bootstrap.input name="description" value="${senv.description}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="默认值">
            <@bootstrap.input name="defValue" value="${senv.defValue}"/>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</@layout.cleanHtml>