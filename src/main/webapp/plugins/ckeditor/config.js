/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	// Adding drag and drop image upload.
	config.extraPlugins= 'print,format,font,colorbutton,justify,image2,uploadimage,codesnippet',

	config.toolbar = [
		{ name: 'tools', items: [ 'Maximize' ] },
		{ name: 'document', items: [ 'Source' ] },
		{ name: 'styles', items: ['Format', 'FontSize' ] },
		{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
		{ name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike'] },
		{ name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ] },
		{ name: 'links', items: [ 'Link', 'Unlink' ] },
		{ name: 'insert', items: [ 'Image', 'HorizontalRule', 'Smiley',  'CodeSnippet'  ] }
	];

	config.extraAllowedContent='h3{clear};h2{line-height};h2 h3{margin-left,margin-top}',

	//使用zenburn的代码高亮风格样式 PS:zenburn效果就是黑色背景
	//如果不设置着默认风格为default
	config.codeSnippet_theme= 'github',
	config.uploadUrl= 'upload?command=QuickUpload&type=Files&responseType=json',

	// Configure your file manager integration. This example uses CKFinder 3 for PHP.
	config.filebrowserBrowseUrl= 'ckfinder/ckfinder.html',
	config.filebrowserImageBrowseUrl= 'ckfinder/ckfinder.html?type=Images',
	config.filebrowserUploadUrl= 'upload?command=QuickUpload&type=Files',
	config.filebrowserImageUploadUrl= 'uploadImage?command=QuickUpload&type=Images',

	//将Format下拉列表中列出的块元素列表减少到最常用的。
	//config.format_tags= 'p;h1;h2;h3;pre'

	config.removeDialogTabs= 'image:advanced;link:advanced',

	config.height= 450
};