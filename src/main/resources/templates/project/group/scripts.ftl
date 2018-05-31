<#-- @ftlvariable name="groupScripts" type="java.util.List<com.slyak.mirrors.domain.ProjectGroupScript>" -->
<@layout.layout_project_group title="脚本列表" btnCreate={'title':'管理脚本','modal':true,'url':'/project/group/scriptsPicker?id=${RequestParameters.id}','showSubmit':true}>
    <@slyakUI.jqueryui/>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">操作系统</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody class="sortable">
        <#list groupScripts as groupScript>
            <#assign script = groupScript.script/>
        <tr data-gsId="${groupScript.id}">
            <td><i class="fas fa-code"></i><a href="<@slyak.query url="/script/content?id=${script.id}"/>"
                                              target="_blank">${script.name}</a></td>
            <td>${script.osName}:${script.osVersions?join(",")}</td>
            <td>
                <#if script.envs?? && script.envs?size gt 0>
                    <@bootstrap.a href="/project/group/script?id=${groupScript.id}" modal="true" showSubmit=true title="变量设置"/>
                </#if>
                <a href="<@slyak.query url='/project/group/deleteScript?id=${groupScript.id}'/>"
                   class="confirm ajax">删除</a>
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
    <i class="fa fa-info-circle pr-1"></i>可以拖动上面的行进行排序，来决定脚本执行顺序！
</div>

<script>
    $(function () {
        $(".sortable").sortable({
            cursor: "move",
            items: "tr",
            stop: function () {
                var gsIds = $.map($(".sortable tr"), function (el) {
                    return $(el).attr("data-gsId");
                });
                jQuery.ajax({
                    url: "<@slyak.query url='/project/group/scriptOrders'/>",
                    type: "POST",
                    data: JSON.stringify(gsIds),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function () {
                    }
                });
            }
        });
    });
</script>
</@layout.layout_project_group>