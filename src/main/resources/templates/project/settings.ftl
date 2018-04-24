<@layout.project title="配置" btnCreate={'title':'删除','url':'/project/delete'}>
<div class="settings">
    <@bootstrap.form action="">
        <@bootstrap.formgroup label="项目名称">
            <@bootstrap.input name="name" value="${project.name}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="描述">
            <@bootstrap.textarea name="name" value="${project.desc}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="">
            <button type="submit" class="btn btn-primary btn-lg">保存</button>
        </@bootstrap.formgroup>
    </@bootstrap.form>
</div>
</@layout.project>