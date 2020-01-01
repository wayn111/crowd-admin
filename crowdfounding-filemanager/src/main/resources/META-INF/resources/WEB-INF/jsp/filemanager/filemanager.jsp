<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowdfounding 文件管理</title>
    <meta name="keywords" content="crowdfounding,基于H+,后台HTML,响应式后台">
    <meta name="description"
          content="design by wayn">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <!-- elFinder CSS (REQUIRED) -->
    <link rel="stylesheet" type="text/css" href="${_ctx }/static/plugin/elfinder/css/theme.css">
    <script src="${_ctx }/static/plugin/elfinder/js/jquery-1.11.1.js"></script>
    <!-- elFinder JS (REQUIRED) -->
    <script src="${_ctx }/static/plugin/elfinder/js/elfinder.full.js"></script>
    <!-- elFinder translation (OPTIONAL) -->
    <script src="${_ctx }/static/plugin/elfinder/js/i18n/elfinder.zh_CN.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/js/extras/quicklook.googledocs.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/ace/ace.js" type="text/javascript" charset="utf-8"></script>
    <script src="${_ctx }/static/plugin/elfinder/ace/ext-modelist.js" type="text/javascript" charset="utf-8"></script>
    <script src="${_ctx }/static/plugin/elfinder/ace/ext-settings_menu.js"></script>
    <script src="${_ctx }/static/plugin/elfinder/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function () {
            elFinder.prototype.loadCss(_ctx + '/static/plugin/elfinder/jquery-ui-1.12.1.custom/jquery-ui.css');
            $('#elfinder').elfinder({
                url: _ctx + '/filemanager/connector',
                lang: 'zh_CN',
                height: window.innerHeight - 20,
                commandsOptions: {
                    edit: {
                        editors: [
                            {
                                info: {
                                    name: '编辑',
                                    urlAsContent: false
                                },
                                // ACE Editor
                                // `mimes` is not set for support everything kind of text file
                                load: function (textarea) {
                                    var self = this,
                                        dfrd = $.Deferred(),
                                        cdn = _ctx + '/static/plugin/elfinder/ace/',
                                        init = function () {
                                            if (typeof ace === 'undefined') {
                                                console.log(cdn);
                                                this.fm.loadScript([
                                                    cdn + '/ace.js',
                                                    cdn + '/ext-modelist.js',
                                                    cdn + '/ext-settings_menu.js',
                                                    cdn + '/ext-language_tools.js'
                                                ], start);
                                            } else {
                                                start();
                                            }
                                        },
                                        start = function () {
                                            var editor, editorBase, mode,
                                                ta = $(textarea),
                                                taBase = ta.parent(),
                                                dialog = taBase.parent(),
                                                id = textarea.id + '_ace',
                                                ext = self.file.name.replace(/^.+\.([^.]+)|(.+)$/, '$1$2').toLowerCase(),
                                                // MIME/mode map
                                                mimeMode = {
                                                    'text/x-php': 'php',
                                                    'application/x-php': 'php',
                                                    'text/html': 'html',
                                                    'application/xhtml+xml': 'html',
                                                    'text/javascript': 'javascript',
                                                    'application/javascript': 'javascript',
                                                    'text/css': 'css',
                                                    'text/x-c': 'c_cpp',
                                                    'text/x-csrc': 'c_cpp',
                                                    'text/x-chdr': 'c_cpp',
                                                    'text/x-c++': 'c_cpp',
                                                    'text/x-c++src': 'c_cpp',
                                                    'text/x-c++hdr': 'c_cpp',
                                                    'text/x-shellscript': 'sh',
                                                    'application/x-csh': 'sh',
                                                    'text/x-python': 'python',
                                                    'text/x-java': 'java',
                                                    'text/x-java-source': 'java',
                                                    'text/x-ruby': 'ruby',
                                                    'text/x-perl': 'perl',
                                                    'application/x-perl': 'perl',
                                                    'text/x-sql': 'sql',
                                                    'text/xml': 'xml',
                                                    'application/docbook+xml': 'xml',
                                                    'application/xml': 'xml'
                                                };

                                            // set basePath of ace
                                            ace.config.set('basePath', cdn);

                                            // set base height
                                            taBase.height(taBase.height());

                                            // detect mode
                                            mode = ace.require('ace/ext/modelist').getModeForPath('/' + self.file.name).name;
                                            if (mode === 'text') {
                                                if (mimeMode[self.file.mime]) {
                                                    mode = mimeMode[self.file.mime];
                                                }
                                            }

                                            // show MIME:mode in title bar
                                            taBase.prev().children('.elfinder-dialog-title').append(' (' + self.file.mime + ' : ' + mode.split(/[\/\\]/).pop() + ')');

                                            // TextArea button and Setting button
                                            $('<div class="ui-dialog-buttonset"/>').css('float', 'left')
                                                .append(
                                                    $('<button>文本框</button>')
                                                        .button()
                                                        .on('click', function () {
                                                            if (ta.data('ace')) {
                                                                ta.removeData('ace');
                                                                editorBase.hide();
                                                                ta.val(editor.session.getValue()).show().focus();
                                                                $(this).text('编辑器');
                                                            } else {
                                                                ta.data('ace', true);
                                                                editorBase.show();
                                                                editor.setValue(ta.hide().val(), -1);
                                                                editor.focus();
                                                                $(this).text('文本框');
                                                            }
                                                        })
                                                )
                                                .append(
                                                    $('<button>Ace editor setting</button>')
                                                        .button({
                                                            icons: {
                                                                primary: 'ui-icon-gear',
                                                                secondary: 'ui-icon-triangle-1-e'
                                                            },
                                                            text: false
                                                        })
                                                        .on('click', function () {
                                                            editor.showSettingsMenu();
                                                        })
                                                )
                                                .prependTo(taBase.next());

                                            // Base node of Ace editor
                                            editorBase = $('<div id="' + id + '" style="width:100%; height:100%;"/>').text(ta.val()).insertBefore(ta.hide());

                                            // Ace editor configure
                                            ta.data('ace', true);
                                            editor = ace.edit(id);
                                            ace.require('ace/ext/language_tools');
                                            ace.require('ace/ext/settings_menu').init(editor);
                                            editor.$blockScrolling = Infinity;
                                            editor.setOptions({
                                                theme: 'ace/theme/dawn',
                                                mode: 'ace/mode/' + mode,
                                                fontSize: '14px',
                                                wrap: true,
                                                enableBasicAutocompletion: true,
                                                enableSnippets: true,
                                                enableLiveAutocompletion: true
                                            });
                                            editor.commands.addCommand({
                                                name: "saveFile",
                                                bindKey: {
                                                    win: 'Ctrl-s',
                                                    mac: 'Command-s'
                                                },
                                                exec: function (editor) {
                                                    self.doSave();
                                                }
                                            });
                                            editor.commands.addCommand({
                                                name: "closeEditor",
                                                bindKey: {
                                                    win: 'Ctrl-w|Ctrl-q',
                                                    mac: 'Command-w|Command-q'
                                                },
                                                exec: function (editor) {
                                                    self.doCancel();
                                                }
                                            });

                                            editor.resize();

                                            dfrd.resolve(editor);
                                        };

                                    // init & start
                                    init();

                                    return dfrd;
                                },
                                close: function (textarea, instance) {
                                    if (instance) {
                                        instance.destroy();
                                        $(textarea).show();
                                    }
                                },
                                save: function (textarea, instance) {
                                    instance && $(textarea).data('ace') && (textarea.value = instance.session.getValue());
                                },
                                focus: function (textarea, instance) {
                                    instance && $(textarea).data('ace') && instance.focus();
                                },
                                resize: function (textarea, instance, e, data) {
                                    instance && instance.resize();
                                }
                            }
                        ]
                    },
//                    quicklook : {
//                        // to enable preview with Google Docs Viewer
//                        googleDocsMimes : ['application/pdf', 'image/tiff', 'application/vnd.ms-office', 'application/msword', 'application/vnd.ms-word', 'application/vnd.ms-excel', 'application/vnd.ms-powerpoint', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet']
//                    }
                }
            });
        });
    </script>
</head>
<body>

<div id="elfinder"></div>

</body>
</html>
