<@layout.group title="设置">
<div class="settings">
    <@bootstrap.form action="">
        <@bootstrap.formgroup label="分组名称" required=true>
            <@bootstrap.input name="name" value="${group.name}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="操作系统" required=true>
            <@bootstrap.select name="os" value="${group.os}" options=[
            {'title':'CentOS-7.0','value':'123'},
            {'title':'CentOS-7.3','value':'456'}
            ]/>
            <a class="ml-3 btn-link" href="<@slyak.query url="/admin/os"/>">没有匹配的操作系统？</a>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="">
            <button type="submit" class="btn btn-primary btn-lg">保存</button>
        </@bootstrap.formgroup>
    </@bootstrap.form>
</div>
</@layout.group>