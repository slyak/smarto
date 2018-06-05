<#-- @ftlvariable name="scriptFile" type="com.slyak.smarto.domain.ScriptFile" -->
<@layout.cleanHtml>
    <@slyakUI.form title="上传脚本" action="/script/file" id="uploadForm">
        <@bootstrap.formgroup label="文件" required=true left=3 right=9>
            <@bootstrap.input type="file" name="file" value="" class="upload" />
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="目标文件夹" required=true left=3 right=9>
            <@bootstrap.input type="text" name="scpPath" value="${scriptFile.scpPath}"/>
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="描述" required=true left=3 right=9>
            <@bootstrap.input type="text" name="description" value="${scriptFile.description}"/>
        </@bootstrap.formgroup>
        <@bootstrap.input type="hidden" name="scriptId" value="${scriptFile.scriptId}"/>
        <@bootstrap.input type="hidden" name="id" value="${scriptFile.id}"/>
        <@bootstrap.input type="hidden" name="globalFileId" value="${scriptFile.globalFileId}"/>
    </@slyakUI.form>
<script>
    function onSubmit() {
        if (!$("input[name=globalFileId]").val()) {
            alert("必须先上传文件");
            return false;
        }
        return true;
    }

    function setupScriptFileId(data) {
        var files = data.response;
        for (var i = 0; i < files.length; i++) {
            $("input[name=globalFileId]").val(files[0].extra.id);
        }
    }

    function removeScriptFile() {
        $("input[name=globalFileId]").val("");
    }
</script>
    <@bootstrap.fileupload cssSelector=".upload" uploadUrl="/file/upload" deleteUrl="/script/file/delete"  maxFileCount=1 imageWidth=100
    showPreview=true onUploaded="setupScriptFileId" onCleared="removeScriptFile" initialPreviewConfig=initConfig editable=!initConfig?has_content/>
</@layout.cleanHtml>