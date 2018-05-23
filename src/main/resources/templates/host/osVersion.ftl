<#-- @ftlvariable name="host" type="com.slyak.mirrors.domain.Host" -->
<#-- @ftlvariable name="versions" type="java.util.List<String>" -->
<div class="form-group row">
    <label class="col-sm-3 col-form-label">适用版本<span class="text-danger">*</span></label>
    <div class="col-sm-9">
        <div class="mt-2">
            <select name="osVersion">
            <#list versions as ver>
                <option value="${ver}" <#if host.osVersion == ver>selected</#if>>${ver}</option>
            </#list>
            </select>
        </div>
    </div>
</div>