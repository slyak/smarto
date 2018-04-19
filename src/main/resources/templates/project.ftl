<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Group>" -->

<#assign right>
<form method="post" action="<@slyak.query url="/project"/>" autocomplete="off">
    <div class="form-group row">
        <label for="script" class="col-sm-2">名称</label>
        <div class="col-sm-10"><input class="form-control" name="name" placeholder="输入项目名称"/></div>
    </div>

    <div class="form-group row">
        <label for="script" class="col-sm-2">机器组</label>
        <div class="col-sm-10">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">名称</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <tbody>
                <#--<#list page.content as group>-->
                    <#list 1..3 as i>
                    <tr>
                    <#--<td>${group.id}</td>
                    <td>${group.name}</td>-->
                        <td>123</td>
                        <td>214</td>
                        <td>
                            <a class="btn-link" href="<@slyak.query url="/group?id=1" />">编辑</a>
                            <a class="btn-link" href="<@slyak.query url="/group/delete"/>">删除</a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <button type="submit" class="btn float-right">保存</button>
</form>
</#assign>

<#assign left>
<div style="width: 100%;text-align: center;padding:15px;">
    <img src="http://192.168.230.8:3990/projects/MT/avatar.png?s=256&amp;v=1476253074022" alt="mssg-test" style="">
    <div style="font-size: 20px;color:#333;line-height: 70px;">大数据基础</div>
</div>
</#assign>

<@layout.leftRight title='详情' left=left right=right/>