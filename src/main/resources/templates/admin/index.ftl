<@layout.admin title="全局设置">
    <@slyakUI.form action="/admin/filepath">
        <@bootstrap.formgroup label="站点地址" required=true>
            <@bootstrap.input name="domain"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="文件存储位置" required=true>
            <@bootstrap.input name="homePath"/>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</@layout.admin>