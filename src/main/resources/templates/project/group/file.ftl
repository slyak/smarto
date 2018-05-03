<@layout.detail title="文件详情" action="/project/group/file">
    <@bootstrap.formgroup label="详情" required=true>
    <div class="mt-2">
        <@bootstrap.radios name="fileType" data=[{'title':'普通文件','value':'NORMAL'},{'title':'URL','value':'URL'},{'title':'YUM','value':'YUM'}]/>
    </div>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="地址" required=true>
        <@bootstrap.input type="text" name="name" value="${file.url}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="存储路径" required=true>
        <@bootstrap.input type="text" name="name" value="${group.path}"/>
    </@bootstrap.formgroup>
</@layout.detail>