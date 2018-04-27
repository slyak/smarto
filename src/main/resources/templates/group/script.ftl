<@layout.detail title="上传脚本" action="/group/" enctype="multipart/form-data">
    <@bootstrap.formgroup label="脚本文件" required=true left=3 right=9>
        <@bootstrap.input type="file" name="file" value="${script.file}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="版本号" required=true left=3 right=9>
        <@bootstrap.input type="text" name="version" value="${script.version}"/>
    </@bootstrap.formgroup>
</@layout.detail>