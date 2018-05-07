<@layout.detail title="创建主机" action="/">
    <@bootstrap.formgroup label="主机名" required=true left=3>
        <@bootstrap.input type="text" name="name" value="${group.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="IP" required=true left=3>
        <@bootstrap.input type="text" name="ip" value="${group.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="SSH端口号" required=true left=3>
        <@bootstrap.input type="text" name="sshPort" value="22"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="描述" required=true left=3>
        <@bootstrap.textarea name="desc" value="${group.desc}"/>
    </@bootstrap.formgroup>
</@layout.detail>