<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Group>" -->
<#assign right>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
        <#list 1..3 as i>
        <tr>
            <td>214</td>
            <td>
                <a class="btn-link" href="<@slyak.query url="/group?id=1" />">编辑</a>
                <a class="btn-link" href="<@slyak.query url="/group/delete"/>">删除</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</#assign>

<#assign left>
<div style="text-align: center;">
    <img src="http://192.168.230.8:3990/projects/MT/avatar.png?s=256&amp;v=1476253074022" alt="mssg-test" style="">
    <div style="font-size: 20px;color:#333;line-height: 53px;padding-top: 10px">大数据基础</div>
    <div style="text-align: left;line-height: 20px;padding: 10px 0 4px;font-size: 12px;font-weight: 900;color: rgb(112, 112, 112)">
        ACTIONS
    </div>
</div>
</#assign>

<@layout.leftRight title='机器组' left=left right=right/>