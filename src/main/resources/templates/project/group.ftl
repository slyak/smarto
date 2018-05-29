<#-- @ftlvariable name="projectGroup" type="com.slyak.mirrors.domain.ProjectGroup" -->
<#macro settings>
<input type="hidden" name="id" value="${projectGroup.id}">
<input type="hidden" name="projectId" value="${RequestParameters.projectId}">
    <@bootstrap.formgroup label="名称" required=true>
        <@bootstrap.input type="text" name="name" value="${projectGroup.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="描述" required=true>
        <@bootstrap.textarea name="description" value="${projectGroup.description}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="执行权重" required=true>
        <@bootstrap.input type="text" name="order" value="${projectGroup.order}"/>
    </@bootstrap.formgroup>
</#macro>

<#if projectGroup.id?has_content>
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