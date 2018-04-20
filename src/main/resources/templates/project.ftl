<#-- @ftlvariable name="slyakRequestContext" type="com.slyak.web.support.freemarker.SlyakRequestContext" -->
<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Group>" -->
<#assign right>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">描述</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-desktop"></i><a href="<@slyak.query url="/group"/>">NameNode</a></td>
        <td>NameNode JobExecutor HiveManager</td>
    </tr>
    <tr>
        <td><i class="fas fa-desktop"></i><a href="<@slyak.query url="/group"/>">DataNode</a></td>
        <td>DataNode TaskExecutor HiveNode</td>
    </tr>
    <tr>
        <td><i class="fas fa-desktop"></i><a href="<@slyak.query url="/group"/>">EdgeNode</a></td>
        <td>Zookeeper BI ReportServer</td>
    </tr>
    </tbody>
</table>
</#assign>

<#assign left>
<div>
    <div class="text-center">
        <img src="http://192.168.230.8:3990/projects/MT/avatar.png?s=256&amp;v=1476253074022" alt="mssg-test" style="">
        <div style="font-size: 20px;color:#333;line-height: 53px;padding-top: 10px">大数据基础</div>
    </div>
    <div class="list-wrapper">
        <div class="list-title">ACTIONS</div>
        <ul>
            <li class="list-item">
                <a href="<@slyak.query url="/group"/>">
                    <i class="fas fa-plus"></i>
                    <span>创建机器组</span>
                </a>
            </li>
        </ul>
    </div>
    <hr class="mt-1 mb-1"/>
    <div class="list-wrapper">
        <div class="list-title">NAVIGATION</div>
        <ul>
            <li class="list-item">
                <a href="<@slyak.query url="/project"/>"
                   <#if slyakRequestContext.isSameUrl("/project")>class="active" </#if>>
                    <i class="fas fa-desktop"></i>
                    <span>机器组列表</span>
                </a>
            </li>
        </ul>
    </div>
    <hr class="mt-1 mb-1"/>
    <div class="list-wrapper">
        <ul>
            <li class="list-item">
                <a href="<@slyak.query url="/project"/>"
                   <#if slyakRequestContext.isSameUrl("/settings")>class="active" </#if>>
                    <i class="fas fa-cog"></i>
                    <span>配置</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</#assign>

<@layout.rightMain title='机器组列表' left=left right=right/>