<#-- @ftlvariable name="requestId" type="java.lang.String" -->
<@layout.cleanHtml>
    <@ace.cssAndJs/>
    <@sockjs.cssAndJs/>
<textarea id="logArea" style=""></textarea>
<#--<div class="mt-2">
    <a href="#"><i class="fa fa-desktop mr-1"></i>进入全屏模式</a>
</div>-->
<script>
    var editor;
    var callback = function (result) {
        console.log(result);
        var body = eval("(" + result.body + ")");
        var len = editor.session.getLength();
        editor.session.insert({row: len, column: 0}, '\n' + body.line);
    };
</script>
    <@ace.init cssSelector="#logArea" mode="sh" theme="tomorrow" minLines=20 maxLines=20 editable=false>
    window['editor']=editor;
    </@ace.init>

    <@sockjs.connect topics=[
    {"name":"/ssh/receive/${requestId}","callback":"callback"}
    ]>
    ss.connect(function(){
    ss.send("/ssh/logs",{'id':'${requestId}','batchId':${RequestParameters.batchId},'hostId':${RequestParameters.hostId}});
    });
    </@sockjs.connect>
</@layout.cleanHtml>