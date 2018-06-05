<#-- @ftlvariable name="os" type="com.slyak.smarto.domain.OS" -->
<@layout.cleanHtml>
    <@slyakUI.form action="/admin/os">
    <input type="hidden" name="id" value="${os.id}">
        <@bootstrap.formgroup label="操作系统名称" required=true>
            <@bootstrap.input name="os" value="${os.os}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="版本号" required=true>
            <#assign vs><#if os?? && os.versions?has_content>${os.versions?join(",")}</#if></#assign>
            <@bootstrap.input name="versions" placeholder="版本号之间用逗号隔开" value="${vs}"/>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</@layout.cleanHtml>