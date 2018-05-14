<@layout.layout_detail title="创建项目" action="/project">
<div class="settings">
    <@slyakUI.form action="">
        <@bootstrap.formgroup label="项目名称">
            <@bootstrap.input name="name" value="${project.name}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="描述">
            <@bootstrap.textarea name="name">${project.desc}</@bootstrap.textarea>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</div>
</@layout.layout_detail>