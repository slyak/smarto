<#-- @ftlvariable name="global" type="com.slyak.smarto.domain.Global" -->
<@layout.layout_admin title="全局设置">
    <@slyakUI.form action="/admin/global" id="globalForm">
    <input type="hidden" name="id" value="${global.id}">
        <@bootstrap.formgroup label="清除未使用的文件" right=3>
        <div class="pt-2">
            <a href="<@slyak.query url="/file/clean"/>" class="mt-3 btn ajax confirm">清除</a>
        </div>
        </@bootstrap.formgroup>
    </@slyakUI.form>
<script>
    function submitThisForm(ret) {
        $("input[name=testHostId]").val(ret.id);
        return true;
    }
</script>
</@layout.layout_admin>