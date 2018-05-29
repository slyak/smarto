<#-- @ftlvariable name="hosts" type="java.util.List<com.slyak.mirrors.domain.Host>" -->
<@layout.cleanHtml>
    <@slyakUI.form action="/project/group/addScripts">
    <input type="hidden" name="groupId" value="${RequestParameters.id}">
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>名称</th>
            <th>IP</th>
            <th>操作系统</th>
        </tr>
        </thead>
        <tbody>
            <#list hosts as host>
            <tr>
                <td><input type="checkbox" name="hostIds" value="${host.id}"></td>
                <td>${host.name}</td>
                <td>${host.ip}</td>
                <td>${host.osName}:${host.osVersion}</td>
            </tr>
            </#list>
        </tbody>
    </table>
    </@slyakUI.form>
</@layout.cleanHtml>