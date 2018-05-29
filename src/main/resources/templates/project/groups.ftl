<#-- @ftlvariable name="project" type="com.slyak.mirrors.domain.Project" -->
<#-- @ftlvariable name="groups" type="java.util.List<com.slyak.mirrors.domain.ProjectGroup>" -->
<@layout.layout_project title='分组列表' btnCreate={"title":"创建分组","url":"/project/group?projectId=${project.id}", 'modal':true,'showSubmit':true}>
    <@slyakUI.jqueryui/>
<div class="">

</div>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">描述</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody class="sortable">
        <#list groups as group>
        <tr data-groupId="${group.id}">
            <td><i class="fas fa-boxes"></i>${group.name}</td>
            <td>${group.description}</td>
            <td>
                <a href="<@slyak.query url="/project/group/hosts?id=${group.id}"/>">管理</a>
                <@slyakUI.a href="/project/group/delete?id=${group.id}">删除</@slyakUI.a>
            </td>
        </tr>
        <#else >
        <tr>
            <td colspan="3" class="text-center">暂无记录</td>
        </tr>
        </#list>
    </tbody>
</table>
<div class="alert alert-primary" group="alert">
    <i class="fa fa-info-circle pr-1"></i>可以拖动上面的行进行排序，来决定分组执行顺序！
</div>

<script>
    $(function () {
        $(".sortable").sortable({
            cursor: "move",
            items: "tr",
            stop: function () {
                var groupIds = $.map($(".sortable tr"), function (el) {
                    return $(el).attr("data-groupId");
                });
                jQuery.ajax({
                    url: "<@slyak.query url='/project/group/orders'/>",
                    type: "POST",
                    data: JSON.stringify(groupIds),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function () {
                    }
                });
            }
        });
    });
</script>
</@layout.layout_project>