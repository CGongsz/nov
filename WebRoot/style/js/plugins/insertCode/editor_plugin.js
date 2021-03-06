(function () {
    tinymce.PluginManager.requireLangPack('insertCode');
    tinymce.create('tinymce.plugins.insertCodePlugin', {
        init: function (ed, url) {
            var domainPostfix = location.hostname.substring(location.hostname.lastIndexOf(".") + 1, location.hostname.length);
            ed.addCommand('mceinsertCode', function () {
                ed.windowManager.open({ file: '//util.cnblogs.' + domainPostfix + '/InsertCode.aspx', width: 550, height: 400, inline: 1 }, { plugin_url: url, some_custom_arg: 'custom arg' });
            });
            ed.addButton('insertCode', { title: '插入代码（推荐）', cmd: 'mceinsertCode', image: url + '/images/insertCode.gif' });
            ed.onNodeChange.add(function (ed, cm, n) { cm.setActive('insertCode', n.nodeName == 'IMG'); });
        }, createControl: function (n, cm) { return null; }, getInfo: function () { return { longname: 'insertCode plugin', author: 'CNBlogs', authorurl: 'http://www.cnblogs.com', infourl: 'http://www.cnblogs.com', version: "1.0" }; }
    });
    tinymce.PluginManager.add('insertCode', tinymce.plugins.insertCodePlugin);
})();



