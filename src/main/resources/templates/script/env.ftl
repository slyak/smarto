<@layout.cleanHtml>
    <@slyakUI.form action="">
        <@bootstrap.formgroup label="键" required=true>
            <@bootstrap.input name="key"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="描述" required=true>
            <@bootstrap.input name="description"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="默认值">
            <@bootstrap.input name="defValue"/>
        </@bootstrap.formgroup>
    </@slyakUI.form>
</@layout.cleanHtml>