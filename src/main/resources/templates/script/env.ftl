<#-- @ftlvariable name="env" type="com.slyak.mirrors.domain.ScriptEnv" -->
<@layout.cleanHtml>
    <@slyakUI.form action="/script/env">
    <input type="hidden" name="scriptId" value="${RequestParameters.scriptId}">
        <@bootstrap.formgroup label="键" required=true>
            <@bootstrap.input name="key" value="${env.key}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="描述" required=true>
            <@bootstrap.input name="description" value="${env.description}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="默认值">
            <@bootstrap.input name="defValue" value="${env.defValue}"/>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</@layout.cleanHtml>