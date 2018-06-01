<#-- @ftlvariable name="oss" type="java.util.List<com.slyak.web.ui.Option>" -->
<#-- @ftlvariable name="script" type="com.slyak.mirrors.domain.Script" -->
<#macro settings left=3>
<input type="hidden" name="id" value="${script.id}">
    <@bootstrap.formgroup label="名称" required=true left=left>
        <@bootstrap.input name="name" value="${script.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="操作系统" required=true left=left>
        <@bootstrap.select name="osName" options=oss value="${script.osName}" mention="选择操作系统"/>
        <@slyakUI.a href="/admin/oss">没有想要的操作系统？赶紧去配置一个！</@slyakUI.a>
    </@bootstrap.formgroup>
<div id="versionsHolder"></div>
<script>
    $(function () {
        var osName = '${script.osName}';
        var baseUrl = "<@slyak.query url='/script/osVersions'/>?id=${RequestParameters.id}";

        function showVersions() {
            if (osName) {
                $("#versionsHolder").load(baseUrl + "&osName=" + osName);
            }
        }

        $("select[name=osName]").change(function (e) {
            $("#versionsHolder").html("");
            osName = $(this).find(":selected").val();
            showVersions();
        });

        showVersions();
    });
</script>
</#macro>

<#if script.id?has_content>
    <@layout.layout_script title="">
        <@layout.cardForm title="配置" action="/script">
            <@settings/>
        </@layout.cardForm>
    </@layout.layout_script>
<#else >
    <@layout.layout_detail title="创建脚本" action="/script">
        <@settings/>
    </@layout.layout_detail>
</#if>