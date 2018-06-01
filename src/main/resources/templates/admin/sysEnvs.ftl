<#-- @ftlvariable name="sysEnvs" type="java.util.List<com.slyak.mirrors.dto.SysEnv>" -->
<@layout.cleanHtml>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>名称</th>
        <th>描述</th>
        <th>数据结构</th>
    </tr>
    </thead>
    <tbody>
        <#list sysEnvs as sysEnv>
        <tr>
            <td>${sysEnv.name}</td>
            <td>${sysEnv.description}</td>
            <td>
                <pre>${sysEnv.structure}</pre>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@layout.cleanHtml>