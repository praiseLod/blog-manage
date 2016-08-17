/**
 * 菜单添加对话窗口
 */
Ext.define('BlogMgr.view.menu.MenuAddDialog',{
			id : 'menuAddDialog',
			uses:['BlogMgr.view.menu.MenuAddForm',
				  'Ext.ux.TreePicker',
				  'BlogMgr.view.menu.MenuAddDialogController'],
			extend : 'Ext.window.Window',
			alias : ['widget.menuadd_dialog'],
			layout : 'fit',
			title : '菜单添加',
			maximizable : true, // 最大化
			controller:'menuadd_dialog',
			bodyStyle : 'padding : 2px 2px 0',
			shadowOffset : 30, // 投影效果
			modal : true, // 模态
			width : 450,
			glyph : 0xe6cb,
			tools : [{
						type : 'help',
						tooltip : '帮助'
					}],
			buttons : [{
				itemId : 'save',
				text : '保存',
				glyph : 0xe650,
				handler:'userAddSubmit'
			}, {
				itemId : 'close',
				text : '关闭',
				glyph : 0xe6af,
				handler:'closeDialog'
			}],
			items : {
						xtype : 'menuadd_form'
					}
});