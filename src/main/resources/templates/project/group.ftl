<#-- @ftlvariable name="projectGroup" type="com.slyak.mirrors.domain.ProjectGroup" -->
<#macro settings>
<input type="hidden" name="id" value="${projectGroup.id}">
<input type="hidden" name="projectId" value="${projectGroup.projectId}">
    <@bootstrap.formgroup label="名称" required=true>
        <@bootstrap.input type="text" name="name" value="${projectGroup.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="描述" required=true>
        <@bootstrap.textarea name="description">${projectGroup.description}</@bootstrap.textarea>
    </@bootstrap.formgroup>
</#macro>

<#if (!RequestParameters.single??) && projectGroup.id?has_content>
    <@layout.layout_project_group title="">
        <@layout.cardForm title="配置" action="/project/group">
            <@settings/>
        </@layout.cardForm>
    </@layout.layout_project_group>
<#else >
    <@layout.cleanHtml>
        <@slyakUI.form action="/project/group">
            <@settings/>
        </@slyakUI.form>
    </@layout.cleanHtml>
</#if>