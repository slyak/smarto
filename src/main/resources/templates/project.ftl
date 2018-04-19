<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.slyak.mirrors.domain.Group>" -->
<#assign right>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">描述</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><a href="<@slyak.query url="/group"/>">NameNode</a></td>
        <td>NameNode JobExecutor HiveManager</td>
    </tr>
    <tr>
        <td><a href="<@slyak.query url="/group"/>">DataNode</a></td>
        <td>DataNode TaskExecutor HiveNode</td>
    </tr>
    <tr>
        <td><a href="<@slyak.query url="/group"/>">EdgeNode</a></td>
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
    <div style="text-align: left;line-height: 20px;padding: 10px 0 4px;color: rgb(112, 112, 112)">
        <div style="font-size: 12px;font-weight: 900;">ACTIONS</div>
        <ul>
            <li class="action-item">
                <a href="<@slyak.query url="/group"/>">
                    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                        <g fill="#B1B1B1" fill-rule="evenodd">
                            <path d="M13.272 13.272h9.344a1.387 1.387 0 0 0-.01-2.772h-9.334V9.337 1.384a1.387 1.387 0 0 0-2.772.01V10.5H1.394C.617 10.5 0 11.12 0 11.886c0 .771.618 1.386 1.38 1.386h9.16l-.04 9.334c0 .777.62 1.394 1.386 1.394a1.38 1.38 0 0 0 1.386-1.38v-6.733-2.637z"></path>
                        </g>
                    </svg>
                    <span>创建机器组</span>
                </a>
            </li>
        </ul>
    </div>
    <ul style="color: rgb(112, 112, 112)">

    </ul>
</div>
</#assign>

<@layout.leftRight title='机器组列表' left=left right=right/>