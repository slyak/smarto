<#-- @ftlvariable name="project" type="com.slyak.mirrors.domain.Project" -->
<#macro settings left=3>
<input type="hidden" name="id" value="${project.id}">
    <@bootstrap.formgroup label="项目名称">
        <@bootstrap.input name="name" value="${project.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="描述">
        <@bootstrap.textarea name="description">${project.description}</@bootstrap.textarea>
    </@bootstrap.formgroup>
</#macro>

<#if project.id?has_content>
    <@layout.layout_project title="">
        <@layout.cardForm title="配置" action="/project">
            <@settings/>
        </@layout.cardForm>
    </@layout.layout_project>
<#else >
    <@layout.layout_detail title="创建项目" action="/project">
        <@settings/>
    </@layout.layout_detail>
</#if>