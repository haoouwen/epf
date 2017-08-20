/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	  //工具栏是否可以被收缩
	config.toolbarCanCollapse = true;
	 //工具栏默认是否展开
	config.filebrowserImageUploadUrl = '/ckupload.action';

  	config.filebrowserFlashUploadUrl = '/ckupload!flashUpload.action';
  	
  	config.filebrowserUploadUrl = '/ckupload!fileUpload.action'; 
};
