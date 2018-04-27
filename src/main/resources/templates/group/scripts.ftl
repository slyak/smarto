<@layout.group title="脚本列表" btnCreate={'title':'运行脚本','url':'javascript:runscripts()'}>
<script>
    function runscripts() {
        $(window).trigger('runScripts',{scriptId:1});
    }
</script>
<table class="table table-hover table-fa">
    <thead>
    <tr>
        <th scope="col">名称</th>
        <th scope="col">版本号</th>
        <th scope="col">状态</th>
        <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <#--<#list page.content as group>-->
    <tr>
        <td><i class="fas fa-code"></i>CentOS优化</td>
        <td>1.0</td>
        <td>执行成功 已停用</td>
        <td>
            <a class="btn-link mr-3" href="<@slyak.query url="/group/file"/>">查看</a>
            <a class="btn-link" href="<@slyak.query url='/group/file/delete'/>">启用</a>
        </td>
    </tr>
    <tr>
        <td><i class="fas fa-code"></i>AmbariManager安装</td>
        <td>1.1</td>
        <td>执行失败 已启用</td>
        <td>
            <a class="btn-link mr-3" href="<@slyak.query url="/group/file"/>">查看</a>
            <a class="btn-link" href="<@slyak.query url='/group/file/delete'/>">停用</a>
        </td>
    </tr>
    </tbody>
</table>

<h5 class="mt-5">实时运行结果</h5>
<hr/>
    <@ace.cssAndJs/>
    <@sockjs.cssAndJs/>
<ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" id="NameNode1-tab" data-toggle="tab" href="#NameNode1" role="tab"
           aria-controls="home" aria-selected="true">NameNode1</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" id="NameNode2-tab" data-toggle="tab" href="#NameNode2" role="tab" aria-controls="profile"
           aria-selected="false">NameNode2</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" id="NameNode3-tab" data-toggle="tab" href="#NameNode3" role="tab" aria-controls="contact"
           aria-selected="false">NameNode3</a>
    </li>
</ul>
<div class="tab-content">
    <div class="tab-pane fade show active" id="NameNode1" role="tabpanel" aria-labelledby="NameNode1-tab"></div>
    <div class="tab-pane fade" id="NameNode2" role="tabpanel" aria-labelledby="NameNode2-tab"></div>
    <div class="tab-pane fade" id="NameNode3" role="tabpanel" aria-labelledby="NameNode3-tab"></div>
</div>
    <#list 1..3 as i>
    <script>
        var callback${i};
    </script>
        <@ace.init id="NameNode${i}" mode="powershell">
        callback${i}=function(result){
        console.log(result);
        var len = editor.session.getLength();
        console.log(len);
        editor.session.insert({row:len,column:0}, '\r\n'+result.body);
        }
        </@ace.init>
        <@sockjs.connect topics=[
            {"name":"/ssh/logs/NameNode${i}","callback":"callback${i}"}
        ]>
        ss.connect();
        $(window).on('runScripts', function(){
            ss.send("/ssh/exec",{"user":"NameNode${i}","scriptId":1})
        });
        </@sockjs.connect>
    </#list>
</@layout.group>
