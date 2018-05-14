<@layout.layout_detail title="创建脚本" action="/script">
    <@bootstrap.formgroup label="名称" required=true left=3 right=9>
        <@bootstrap.input name="name"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="操作系统" required=true left=3 right=9>
        <@bootstrap.radios name="osName" data=[{'title':'test','value':'test'}]/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="版本范围" left=3 right=9>
    </@bootstrap.formgroup>
</@layout.layout_detail>