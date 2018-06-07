<#-- @ftlvariable name="oss" type="java.util.List<com.slyak.web.ui.Option>" -->
<#-- @ftlvariable name="host" type="com.slyak.smarto.domain.Host" -->
<@layout.layout_detail action="/host" title="${host.id???string('编辑','创建')}主机">
<input type="hidden" name="id" value="${host.id}">
    <@bootstrap.formgroup label="主机名" required=true left=3>
        <@bootstrap.input type="text" name="name" value="${host.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="IP" required=true left=3>
        <@bootstrap.input type="text" name="ip" value="${host.ip}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="SSH端口号" required=true left=3>
        <@bootstrap.input type="text" name="sshPort" value="${host.sshPort}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="用户名" required=true left=3>
        <@bootstrap.input type="text" name="user" value="${host.user}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="密码" required=true left=3>
        <@bootstrap.input type="text" name="password" value="${host.password}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="操作系统" required=true left=3>
        <@bootstrap.select name="osName" options=oss value="${host.osName}" mention="选择操作系统"/>
        <@slyakUI.a href="/admin/oss">没有想要的操作系统？赶紧去配置一个！</@slyakUI.a>
    </@bootstrap.formgroup>
<div id="versionsHolder"></div>
    <@bootstrap.formgroup label="作为测试机" required=true left=3>
    <div class="mt-2"><@slyakUI.checkbox name="testHost" value="${host.testHost}"/></div>
    </@bootstrap.formgroup>
<script>
    $(function () {
        var osName = '${host.osName}';
        var baseUrl = "<@slyak.query url='/host/osVersion'/>?id=${RequestParameters.id}";

        function showVersion() {
            if (osName) {
                $("#versionsHolder").load(baseUrl + "&osName=" + osName);
            }
        }

        $("select[name=osName]").change(function (e) {
            $("#versionsHolder").html("");
            osName = $(this).find(":selected").val();
            showVersion();
        });

        showVersion();
    });
</script>

</@layout.layout_detail>