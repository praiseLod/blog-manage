Ext.define('BlogMgr.view.mgruser.UserListController',{
	uses:['BlogMgr.view.mgruser.UserAddDialog'],
	extend: 'Ext.app.ViewController',
    alias: 'controller.mgruserlist',
    init: function() {},
    addUser:function(){	//新增用户
    	Ext.create('BlogMgr.view.mgruser.UserAddDialog').show();
    }
    
});