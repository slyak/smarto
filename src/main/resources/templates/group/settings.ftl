<@layout.group title="设置">
<div class="settings">
    <@bootstrap.form action="">
        <@bootstrap.formgroup label="分组名称" required=true>
            <@bootstrap.input name="name" value="${group.name}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="操作系统" required=true>
            <@bootstrap.select name="os" value="${group.os}" options=[
            {'title':'普通文件','value':'NORMAL'},
            {'title':'YUM','value':'YUM'},
            {'title':'URL','value':'URL'}
            ]/>
        </@bootstrap.formgroup>
    </@bootstrap.form>
</div>
</@layout.group>