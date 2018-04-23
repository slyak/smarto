<@layout.main title='系统管理'>
<form method="post" action="<@slyak.query url="/admin/filepath"/>">
    <div class="form-group">
        <label>项目文件存储位置</label>
        <input class="form-control" placeholder="/opt/mirrors/${r'${project}'}" type="text" name="storePath">
    </div>
</form>
</@layout.main>