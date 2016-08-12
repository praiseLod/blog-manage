/**
 * 菜单管理 - 主页
 */
Ext.define('BlogMgr.view.menu.MenuList', {
			id : 'menuList',
			extend : 'Ext.panel.Panel',
			alias : ['widget.menulist'],
			uses : ['BlogMgr.view.menu.MenuTree', 
					'BlogMgr.view.menu.MenuGrid',
					'BlogMgr.view.menu.MenuListController',
					'BlogMgr.view.menu.MenuListModel'],
			layout : 'border',
			controller : 'menulist',
			viewModel : {
				type : 'menulist'
			},
			defaults : {
				bodyPadding : 7
			},
			items : [{
						region : 'west',
						title : '',

						width : 200,
						split : true,
						items : {
							xtype : 'menutree'
						}
					}, {
						region : 'center',
						layout : 'fit',
						items : {
							xtype : 'menugrid'
						}
					}]
		});