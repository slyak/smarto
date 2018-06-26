<#-- @ftlvariable name="mirror" type="com.slyak.smarto.domain.Mirror" -->
<@layout.cleanHtml>
    <@ace.cssAndJs/>
    <@slyakUI.form action="/admin/mirror">
        <@bootstrap.formgroup label="名称">
            <@bootstrap.input name="name" value="${mirror.name}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="基础URL">
            <@bootstrap.input name="baseUrl" value="${mirror.baseUrl}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="文件夹根路径">
            <@bootstrap.input name="rootPath" value="${mirror.rootPath}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="初始化脚本">
            <@bootstrap.textarea name="script" id="scriptArea">${mirror.script}</@bootstrap.textarea>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="使用帮助">
            <@bootstrap.input name="help" value="${mirror.help}"/>
        </@bootstrap.formgroup>
    </@slyakUI.form>

    <@ace.init cssSelector="#scriptArea" mode="sh" theme="tomorrow" minLines=20 maxLines=20>
    window['editor'] = editor;
    </@ace.init>
</@layout.cleanHtml>