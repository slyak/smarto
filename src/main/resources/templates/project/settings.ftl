<@layout.project title="配置" btnCreate={'title':'删除','url':'/project/delete'}>
<div class="settings">
    <@slyakUI.form action="">
        <@bootstrap.formgroup label="项目名称">
            <@bootstrap.input name="name" value="${project.name}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="描述">
            <@bootstrap.textarea name="name">${project.desc}</@bootstrap.textarea>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="">
            <button type="submit" class="btn btn-primary btn-lg">保存</button>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</div>
</@layout.project>