<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Group>" -->
<@layout.main title="详情">
<table class="table table-bordered">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">名称</th>
        <th scope="col">操作</th>
    </tr>
    <#--<#list page.content as group>-->
        <#list 1..3 as i>
        <tr>
        <#--<td>${group.id}</td>
        <td>${group.name}</td>-->
            <td>123</td>
            <td>214</td>
            <td>
                <a class="btn-link" href="<@slyak.query url="/group"/>">编辑</a>
                <a class="btn-link" href="<@slyak.query url="/group/delete"/>">删除</a>
            </td>
        </tr>
        </#list>
    </thead>
</table>
</@layout.main>