function delOneInfoa(field_name,actionName,infoid){
			   var tip = '';
				  tip = '确认删除吗?';
			   art.dialog({
					title: '系统信息提示',
					content:'<div class="decorate">'+tip+'</div>',
					okValue: '确定',
					width: '238px',
					cancelValue: '取消',
				    ok: function () {
				        document.getElementById('commonForm').action=actionName;
						document.getElementById('commonText').name = field_name;
						document.getElementById('commonText').value = infoid;
						document.getElementById('commonForm').submit();	  
				    },
				    cancel: function () {
						return true;
				    }
				});
}