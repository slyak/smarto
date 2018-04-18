$(function () {
    $(".fa-minus-circle").live("click", function () {
        var formLine = $(this).parent(".form-inline");
        var available = formLine.siblings().length;
        if (available == 1) {
            alert("保留项无法删除");
            return;
        }
        var fgroup = formLine.parent(".form-group");
        formLine.remove();
        if (fgroup.find(".fa-plus-circle").length == 0) {
            fgroup.find(".fa-minus-circle").last().after('<i class="fas fa-plus-circle fa-lg mb-2 mx-sm-2" style="cursor: pointer;"></i>');
        }
    });
    $(".fa-plus-circle").live("click", function () {
        var formLine = $(this).parent(".form-inline");
        var newLine = formLine.clone();
        newLine.val("");
        formLine.after(newLine);
        $(this).remove();
        console.log(newLine);
    });
    $(".custom-select").live("change", function () {
        var input = $(this).parent().parent(".form-inline").find("input");
        switch ($(this).val()) {
            case 'NORMAL':
                input.attr("type", "file").val("");
                break;
            default :
                input.attr("type", "text").val("");
                break;
        }
    })
});