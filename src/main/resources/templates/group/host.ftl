<@layout.detail title="主机信息" action="/group/">
    <@bootstrap.formgroup label="主机名" required=true left=3 right=9>
        <@bootstrap.input type="text" name="name" value="${host.name}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="IP" required=true left=3 right=9>
        <@bootstrap.input type="text" name="ip" value="${host.ip}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="SSH端口" required=true left=3 right=9>
        <@bootstrap.input type="text" name="sshPort" value="${host.port!22}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="用户名" required=true left=3 right=9>
        <@bootstrap.input type="text" name="user" value="${host.user}"/>
    </@bootstrap.formgroup>
    <@bootstrap.formgroup label="密码" required=true left=3 right=9>
        <@bootstrap.input type="password" name="password" value="${host.password}"/>
    </@bootstrap.formgroup>
</@layout.detail>