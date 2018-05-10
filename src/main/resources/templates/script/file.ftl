<@layout.cleanHtml>
    <@slyakUI.form title="上传脚本" action="/script" enctype="multipart/form-data">
        <@bootstrap.formgroup label="脚本文件" required=true left=3 right=9>
            <@bootstrap.input type="file" name="file" value="setup.pptx" class="upload" />
        </@bootstrap.formgroup>
        <@bootstrap.formgroup label="目标目录" required=true left=3 right=9>
            <@bootstrap.input type="text" name="scpPath" value="/opt/home"/>
        </@bootstrap.formgroup>
    <input type="hidden" name="fids" id="fids">
    <script>
        function setupScriptFileId(data) {
            var files = data.response;
            var fidsVal = $("#fids").val();
            if (fidsVal && fidsVal.length > 0) {

            }

            for (var i = 0; i < files.length; i++) {
            }
        }

        function removeScriptFile(data) {
            console.log(data)
        }

    </script>
    <#assign initConfig= [
    {'caption': "Business-1.jpg", 'size': 762980, 'key': 11,'extra':{'id':11}}
    ]/>

        <@bootstrap.fileupload cssSelector=".upload" uploadUrl="/file/upload" deleteUrl="/file/delete"  maxFileCount=1 imageWidth=100
        showPreview=true onUploaded="setupScriptFileId" onCleared="removeScriptFile" initialPreviewConfig=initConfig/>
    <#--<script src="/webjars/bootstrap-fileinput/js/plugins/piexif.min.js?version=Pmsy"></script>
    <script src="/webjars/bootstrap-fileinput/js/plugins/sortable.min.js?version=Pmsy"></script>
    <script src="/webjars/bootstrap-fileinput/js/plugins/purify.min.js?version=Pmsy"></script>
    <script src="/webjars/bootstrap-fileinput/js/fileinput.min.js?version=Pmsy"></script>
    <script src="/webjars/bootstrap-fileinput/themes/fa/theme.min.js?version=Pmsy"></script>
    <script src="/webjars/bootstrap-fileinput/js/locales/zh.js?version=Pmsy"></script>
    <link href="/webjars/bootstrap-fileinput/css/fileinput.min.css?version=Pmsy" rel="stylesheet">
    <link href="/webjars/slyak-web-bootstrap/fileinput.css?version=Pmsy" rel="stylesheet">
    <script>
        $(function () {
            $(".upload").fileinput({
                uploadUrl: "/file-upload-batch/1",
                uploadAsync: false,
                minFileCount: 2,
                maxFileCount: 5,
                overwriteInitial: false,
                theme:"fa",
                deleteUrl:'/file/delete',
                initialPreview: [
                    // IMAGE DATA
                    'https://placeimg.com/800/460/any',
                    // IMAGE RAW MARKUP
                    '<img src="https://placeimg.com/800/460/any" class="kv-preview-data file-preview-image">',
                    // TEXT DATA
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ut mauris ut libero fermentum feugiat eu et dui. Mauris condimentum rhoncus enim, sed semper neque vestibulum id. Nulla semper, turpis ut consequat imperdiet, enim turpis aliquet orci, eget venenatis elit sapien non ante. Aliquam neque ipsum, rhoncus id ipsum et, volutpat tincidunt augue. Maecenas dolor libero, gravida nec est at, commodo tempor massa. Sed id feugiat massa. Pellentesque at est eu ante aliquam viverra ac sed est.",
                    // PDF DATA
                    'http://kartik-v.github.io/bootstrap-fileinput-samples/samples/pdf-sample.pdf',
                    // VIDEO DATA
                    "http://kartik-v.github.io/bootstrap-fileinput-samples/samples/small.mp4"
                ],
                initialPreviewAsData: true, // defaults markup
                initialPreviewFileType: 'image', // image is the default and can be overridden in config below
                initialPreviewConfig: [
                    {caption: "Business-1.jpg", size: 762980, key: 11},
                    {caption: "Business-2.gz", size: 762980,key: 13},
                    {caption: "Business-2.pdf", size: 762980,key: 13},
                    {caption: "Business-2.ppt", size: 762980,key: 13}
                ],
                preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
                previewFileIconSettings: { // configure your icon file extensions
                    'doc': '<i class="fa fa-file-word text-primary"></i>',
                    'xls': '<i class="fa fa-file-excel text-success"></i>',
                    'ppt': '<i class="fa fa-file-powerpoint text-danger"></i>',
                    'pdf': '<i class="fa fa-file-pdf text-danger"></i>',
                    'zip': '<i class="fa fa-file-archive text-muted"></i>',
                    'htm': '<i class="fa fa-file-code text-info"></i>',
                    'txt': '<i class="fa fa-file-text text-info"></i>',
                    'mov': '<i class="fa fa-file-movie text-warning"></i>',
                    'mp3': '<i class="fa fa-file-audio text-warning"></i>',
                    // note for these file types below no extension determination logic
                    // has been configured (the keys itself will be used as extensions)
                    'jpg': '<i class="fa fa-file-image text-danger"></i>',
                    'gif': '<i class="fa fa-file-image text-muted"></i>',
                    'png': '<i class="fa fa-file-image text-primary"></i>'
                },
                previewFileExtSettings: { // configure the logic for determining icon file extensions
                    'doc': function(ext) {
                        return ext.match(/(doc|docx)$/i);
                    },
                    'xls': function(ext) {
                        return ext.match(/(xls|xlsx)$/i);
                    },
                    'ppt': function(ext) {
                        return ext.match(/(ppt|pptx)$/i);
                    },
                    'zip': function(ext) {
                        return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
                    },
                    'htm': function(ext) {
                        return ext.match(/(htm|html)$/i);
                    },
                    'txt': function(ext) {
                        return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
                    },
                    'mov': function(ext) {
                        return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
                    },
                    'mp3': function(ext) {
                        return ext.match(/(mp3|wav)$/i);
                    }
                }
            }).on("fileuploaded", function (event, data, previewId, index) {
                setupScriptFileId(data);
            }).on("filecleared", function (event, data, msg) {
                console.log(arguments);
                removeScriptFile(data);
            }).on("fileerror", function (event, data, msg) {
            });
        });
    </script>-->
    </@slyakUI.form>
</@layout.cleanHtml>