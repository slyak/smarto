<@layout.layout_admin title="操作系统">
    <@slyakUI.form action="/admin/os">
        <@bootstrap.formgroup label="站点地址" required=true>
            <@bootstrap.input name="domain"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="文件存储位置" required=true>
            <@bootstrap.input name="homePath"/>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</@layout.layout_admin>