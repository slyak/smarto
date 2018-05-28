<#-- @ftlvariable name="projectRole" type="com.slyak.mirrors.domain.ProjectRole" -->
<#macro settings>
<input type="hidden" name="id" value="${projectRole.id}">
<input type="hidden" name="projectId" value="${RequestParameters.projectId}">
    <@bootstrap.formgroup label="名称" required=true>
        <@bootstrap.input type="text" name="name" value="${projectRole.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="描述" required=true>
        <@bootstrap.textarea name="description" value="${projectRole.description}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="执行权重" required=true>
        <@bootstrap.input type="text" name="order" value="${projectRole.order}"/>
    </@bootstrap.formgroup>
</#macro>

<#if projectRole.id?has_content>
    <@layout.layout_project_role title="">
        <@layout.cardForm title="配置" action="/project/role">
            <@settings/>
        </@layout.cardForm>
    </@layout.layout_project_role>
<#else >
    <@layout.cleanHtml>
        <@slyakUI.form action="/project/role">
            <@settings/>
        </@slyakUI.form>
    </@layout.cleanHtml>
</#if>