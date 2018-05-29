<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Script>" -->
<#-- @ftlvariable name="hosts" type="java.util.List<com.slyak.mirrors.domain.Host>" -->
<@layout.cleanHtml>
    <@slyakUI.form action="/project/group/addScripts">
    <@bootstrap.keywordSearch id="scriptSearch"/>
    <input type="hidden" name="groupId" value="${RequestParameters.id}">
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>名称</th>
            <th>操作系统</th>
        </tr>
        </thead>
        <tbody>
            <#list page.content as script>
            <tr>
                <td><input type="checkbox" name="scriptIds" value="${script.id}"></td>
                <td>${script.name}</td>
                <td>${script.osName}:${script.osVersions?join(",")}</td>
            </tr>
            </#list>
        </tbody>
    </table>
    </@slyakUI.form>
</@layout.cleanHtml>