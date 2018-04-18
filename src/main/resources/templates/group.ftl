<#-- @ftlvariable name="group" type="com.slyak.mirrors.domain.Group" -->
<@layout.main title='机器组详情'>
<@slyak.js url="/js/group.js"/>
<style>
    input.form-control{
        width: 250px !important;
    }
</style>
<form method="post" action="<@slyak.query url="/group"/>" autocomplete="off">
    <div class="form-group">
        <label>节点列表</label>
        <#list 1..3 as node>
            <div class="form-inline">
                <div class="form-group mb-2">
                    <input class="form-control" placeholder="服务器IP" type="text" name="ip">
                </div>
                <div class="form-group mb-2 mx-sm-3">
                    <input class="form-control" placeholder="机器名" type="text" name="name">
                </div>
                <div class="form-group mb-2">
                    <input class="form-control" placeholder="用户名" type="text" name="ip">
                </div>
                <div class="form-group mb-2 mx-sm-3">
                    <input class="form-control" placeholder="密码" type="text" name="name">
                </div>
                <i class="fas fa-minus-circle fa-lg mb-2" style="cursor: pointer;"></i>
                <#if !node_has_next>
                <i class="fas fa-plus-circle fa-lg mb-2 mx-sm-2" style="cursor: pointer;"></i>
                </#if>
            </div>
        </#list>
    </div>
    <div class="form-group">
        <label>文件列表</label>
        <#list 1..3 as file>
            <div class="form-inline">
                <div class="form-group mb-2">
                    <input class="form-control" placeholder="输入资源" type="file" name="ip">
                </div>
                <div class="form-group mb-2 mx-sm-3">
                    <select class="custom-select" name="fileType">
                        <option value="NORMAL" selected>文件</option>
                        <option value="URL">URL</option>
                        <option value="YUM">YUM</option>
                    </select>
                </div>
                <i class="fas fa-minus-circle fa-lg mb-2" style="cursor: pointer;"></i>
                <#if !file_has_next>
                    <i class="fas fa-plus-circle fa-lg mb-2 mx-sm-2" style="cursor: pointer;"></i>
                </#if>
            </div>
        </#list>
    </div>
    <div class="form-group">
        <label for="script">脚本</label>
        <textarea class="form-control" id="script" rows="4"></textarea>
    </div>
    <button type="submit" class="btn btn-primary">保存</button>
    <button type="button" class="btn btn-primary">运行</button>
</form>
<div class="bg-light mt-2 mb-2" style="border: 1px solid #ddd">
    <pre class="p-3">
this is a test console
    </pre>
</div>
</@layout.main>