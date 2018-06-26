<#-- @ftlvariable name="global" type="com.slyak.smarto.domain.Global" -->
<@layout.layout_admin title="全局设置">
    <@layout.card title="操作列表">
    <a href="<@slyak.query url="/file/clean"/>" class="mt-3 btn ajax">清除未使用的文件</a>
    </@layout.card>
    <@layout.cardForm title="属性配置" action="/admin/global">
    <input type="hidden" name="id" value="${global.id}">
        <@bootstrap.formgroup label="ip或域名" required=true>
            <@bootstrap.input name="host" value="${global.host}"/>
        </@bootstrap.formgroup>
    </@layout.cardForm>
</@layout.layout_admin>