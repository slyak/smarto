<#-- @ftlvariable name="global" type="com.slyak.smarto.domain.Global" -->
<@layout.layout_admin title="全局设置">
    <@slyakUI.form action="/admin/global" id="globalForm">
    <input type="hidden" name="id" value="${global.id}">
        <@bootstrap.formgroup label="根路径" right=3>
            <@bootstrap.input name="homePath" value="${global.homePath}"/>
        </@bootstrap.formgroup>
    </@slyakUI.form>
<script>
    function submitThisForm(ret) {
        $("input[name=testHostId]").val(ret.id);
        return true;
    }
</script>
</@layout.layout_admin>