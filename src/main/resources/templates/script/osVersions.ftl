<#-- @ftlvariable name="script" type="com.slyak.smarto.domain.Script" -->
<#-- @ftlvariable name="versions" type="java.util.List<String>" -->
<div class="form-group row">
    <label class="col-sm-3 col-form-label">适用版本<span class="text-danger">*</span></label>
    <div class="col-sm-9">
        <div class="mt-2">
        <#list versions as ver>
            <div class="custom-control custom-checkbox custom-control-inline">
                <input id="checkbox_${ver_index}" name="osVersions" value="${ver}" class="custom-control-input"
                       type="checkbox"
                       <#if script?? && script.osVersions?seq_contains(ver)>checked</#if>/>
                <label class="custom-control-label" for="checkbox_${ver_index}">${ver}</label>
            </div>
        </#list>
        </div>
    </div>
</div>