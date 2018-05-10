<@layout.detail title="上传脚本" action="/script" enctype="multipart/form-data">
    <@bootstrap.formgroup label="脚本文件" required=true left=3 right=9>
        <@bootstrap.input type="file" name="file" value="${script.file}" class="upload"/>
    </@bootstrap.formgroup>
    <@bootstrap.fileupload cssSelector=".upload" uploadUrl="/test" maxFileCount=1 imageWidth=100 showPreview=false/>
</@layout.detail>