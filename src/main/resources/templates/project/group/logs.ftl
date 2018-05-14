<@layout.layout_group title="运行日志">
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
<script>
    var editors = {};
    var socketInstance;
    var callback = function (result) {
        console.log(result);
        var body = result.body;
        var groupHostId = body.groupHostId;
        var editor = editors[groupHostId];
        var len = editor.session.getLength();
        console.log(len);
        editor.session.insert({row: len, column: 0}, '\r\n' + body.response);
    };
    function runscripts(groupHostId) {
        socketInstance.send("/ssh/exec", groupHostId);
    }
</script>
    <#list 1..3 as i>
        <#assign editorId = "NameNode${i}"/>
        <@ace.init id="${editorId}" mode="powershell" minLines=30 maxLines=30>
        editors["${editorId}"]=editor;
        </@ace.init>
    </#list>

    <@sockjs.connect topics=[
    {"name":"/ssh/logs","callback":"callback"}
    ]>
    ss.connect();
    socketInstance = ss;
    </@sockjs.connect>
</@layout.layout_group>
