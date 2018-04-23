<@layout.detail title="项目详情" action="/project">
    <@bootstrap.formgroup label="名称" required=true>
        <@bootstrap.input type="text" name="name" value="${group.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="描述" required=true>
        <@bootstrap.textarea name="desc" value="${group.desc}"/>
    </@bootstrap.formgroup>
</@layout.detail>