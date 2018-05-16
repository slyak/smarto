<@layout.layout_script title="运行日志">
    <@ace.cssAndJs/>
    <@sockjs.cssAndJs/>
<textarea id="logArea" style=""></textarea>
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
    <#assign editorId = "#logArea"/>
    <@ace.init cssSelector="${editorId}" mode="sh" theme="tomorrow" minLines=30 maxLines=30>
    editors["${editorId}"]=editor;
    </@ace.init>

    <@sockjs.connect topics=[
    {"name":"/ssh/logs","callback":"callback"}
    ]>
    ss.connect();
    socketInstance = ss;
    </@sockjs.connect>
</@layout.layout_script>
