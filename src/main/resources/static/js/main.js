$(function () {
    var ctx = window['ctx'] || "";

    $("a.test-script").click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        var link = $(this);
        var checking = link.attr("checking");
        if (checking == "1") {
            return;
        }
        link.attr("checking", "1");
        $.get(ctx + "/admin/testHost", function (ret) {
            if (ret) {
                //host is good , then run script
                $.get(link.attr("href"), function () {
                    location.href = ctx + "/logs"
                })
            } else {
                alert("测试主机不可用或未安装docker环境");
                link.attr("checking", "0");
            }
        });
    });
});