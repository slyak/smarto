<@layout.cleanHtml>
    <@ace.cssAndJs/>
    <@sockjs.cssAndJs/>
<textarea id="logArea" style=""></textarea>
<script>
    var editor;
    var callback = function (result) {
        console.log(result);
        var body = result.body;
        var len = editor.session.getLength();
        console.log(len);
        editor.session.insert({row: len, column: 0}, '\r\n' + body.response);
    };
</script>
    <@ace.init cssSelector="#logArea" mode="sh" theme="tomorrow" minLines=30 maxLines=30 editable=false>
    window['editor']=editor;
    </@ace.init>

    <@sockjs.connect topics=[
    {"name":"/ssh/receive/${RequestParameters.batchId}_${RequestParameters.hostId}","callback":"callback"}
    ]>
    ss.connect();
    socketInstance = ss;
    socketInstance.send("/ssh/logs",{'batchId':${RequestParameters.batchId},'testHostId':${RequestParameters.hostId}});
    </@sockjs.connect>
</@layout.cleanHtml>