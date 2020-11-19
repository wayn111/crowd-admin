`<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowd-admin 文件管理</title>
    <meta name="keywords" content="crowd-admin,基于H+,后台HTML,响应式后台">
    <meta name="description"
          content="design by wayn">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <!-- Section CSS -->
    <!-- jQuery UI (REQUIRED) -->
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/jquery/jquery-ui-1.12.0.css" type="text/css">

    <!-- elfinder css -->
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/commands.css"    type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/common.css"      type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/contextmenu.css" type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/cwd.css"         type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/dialog.css"      type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/fonts.css"       type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/navbar.css"      type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/places.css"      type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/quicklook.css"   type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/statusbar.css"   type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/theme.css"       type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/toast.css"       type="text/css">
    <link rel="stylesheet" href="${_ctx }/static/plugin/elfinder/css/toolbar.css"     type="text/css">

    <!-- Section JavaScript -->
    <!-- jQuery and jQuery UI (REQUIRED) -->
    <script src="${_ctx }/static/plugin/elfinder/jquery/jquery-1.12.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="${_ctx }/static/plugin/elfinder/jquery/jquery-ui-1.12.0.js" type="text/javascript" charset="utf-8"></script>

    <!-- elfinder core -->
    <script src="${_ctx }/static/plugin/elfinder/js/elFinder.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/elFinder.version.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/jquery.elfinder.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/elFinder.mimetypes.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/elFinder.options.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/elFinder.options.netmount.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/elFinder.history.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/elFinder.command.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/elFinder.resources.js"></script>

    <!-- elfinder dialog -->
    <script src="${_ctx }/static/plugin/elfinder/js/jquery.dialogelfinder.js"></script>

    <!-- elfinder default lang -->
    <script src="${_ctx }/static/plugin/elfinder/js/i18n/elfinder.en.js"></script>

    <!-- elfinder ui -->
    <script src="${_ctx }/static/plugin/elfinder/js/ui/button.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/contextmenu.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/cwd.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/dialog.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/fullscreenbutton.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/navbar.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/navdock.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/overlay.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/panel.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/path.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/places.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/searchbutton.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/sortbutton.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/stat.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/toast.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/toolbar.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/tree.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/uploadButton.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/viewbutton.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/ui/workzone.js"></script>

    <!-- elfinder commands -->
    <script src="${_ctx }/static/plugin/elfinder/js/commands/archive.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/back.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/chmod.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/colwidth.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/copy.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/cut.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/download.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/duplicate.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/edit.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/empty.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/extract.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/forward.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/fullscreen.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/getfile.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/help.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/hidden.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/hide.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/home.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/info.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/mkdir.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/mkfile.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/netmount.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/open.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/opendir.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/opennew.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/paste.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/places.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/preference.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/quicklook.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/quicklook.plugins.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/reload.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/rename.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/resize.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/restore.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/rm.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/search.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/selectall.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/selectinvert.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/selectnone.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/sort.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/undo.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/up.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/upload.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/commands/view.js"></script>

    <!-- elfinder 1.x connector API support (OPTIONAL) -->
    <script src="${_ctx }/static/plugin/elfinder/js/proxy/elFinderSupportVer1.js"></script>

    <!-- Extra contents editors (OPTIONAL) -->
    <script src="${_ctx }/static/plugin/elfinder/js/extras/editors.default.js"></script>

    <!-- GoogleDocs Quicklook plugin for GoogleDrive Volume (OPTIONAL) -->
    <script src="${_ctx }/static/plugin/elfinder/js/extras/quicklook.googledocs.js"></script>

    <!-- elfinder initialization  -->
    <script>
        $(function() {
            $('#elfinder').elfinder(
                // 1st Arg - options
                {
                    // Disable CSS auto loading
                    cssAutoLoad : false,

                    // Base URL to css/*, js/*
                    baseUrl : '${_ctx }/static/plugin/elfinder/',

                    // Connector URL
                    url : '${_ctx }/elfinder/connector',
                    notifyDelay : 1000,
                    height  : '100%',

                    // Callback when a file is double-clicked
                    getFileCallback : function(file) {
                        // ...
                    },
                },

                // 2nd Arg - before boot up function
                function(fm, extraObj) {
                    // `init` event callback function
                    fm.bind('init', function() {
                        // Optional for Japanese decoder "extras/encoding-japanese.min"
                        delete fm.options.rawStringDecoder;
                        if (fm.lang === 'ja') {
                            fm.loadScript(
                                [ fm.baseUrl + 'js/extras/encoding-japanese.min.js' ],
                                function() {
                                    if (window.Encoding && Encoding.convert) {
                                        fm.options.rawStringDecoder = function(s) {
                                            return Encoding.convert(s,{to:'UNICODE',type:'string'});
                                        };
                                    }
                                },
                                { loadType: 'tag' }
                            );
                        }
                    });

                    // Optional for set document.title dynamically.
                    var title = document.title;
                    fm.bind('open', function() {
                        var path = '',
                            cwd  = fm.cwd();
                        if (cwd) {
                            path = fm.path(cwd.hash) || null;
                        }
                        document.title = path? path + ':' + title : title;
                    }).bind('destroy', function() {
                        document.title = title;
                    });
                }
            );
        });
    </script>
</head>
<body>
<div id="elfinder"></div>
</body>
</html>
