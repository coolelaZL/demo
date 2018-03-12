$(function() {
	loadNotebooks();
	$('#pc_part_1').on('click','li',loadNotes);
	$('#pc_part_2').on('click','li',loadNote);
	$('#save_note').on('click', updateNote);
	
	//绑定添加笔记本按钮
	$('#add_notebook').on('click', demo); 
	
	//绑定添加笔记按钮
	$('#add_note').on('click', showAddNoteDialog); 
	
	//绑定关闭按键事件
	$('#can').on('click', '.cancle,.close', closeDialog);
	
	//绑定添加笔记对话框中的 保存按钮事件
	$('#can').on('click', '.save-note', saveNote);
});
function closeDialog(){
	$('#can').empty();
	$('.opacity_bg').hide();
}

function showAddNoteDialog(){
	$('.opacity_bg').show();
	$('#can').load('alert/alert_note.html');
}

//笔记对话框中的 保存按钮事件
function saveNote(){
	var url = 'note/add.do';
	var notebookId=$('#input_note_title').data('notebookId');
	var title = $('#can #input_note').val();
 
	var data = {userId:getCookie('userId'),
		notebookId:notebookId,
		title:title};
	//console.log(data);
	
	$.post(url, data, function(result){
		if(result.state==0){
			var note=result.data;
			//console.log(note);
			showNote(note);
			var ul = $('#pc_part_2 .contacts-list');
			var li = noteTemplate.replace(
					'[title]', note.title);
			li = $(li);
			li.find('a').addClass('checked');
			ul.find('a').removeClass('checked');
			li.data('notebookId',notebookId);
			ul.prepend(li);
			closeDialog();   
			//123 
		}else{
			alert(result.message);
		}
	});
}

function updateNote(){
	var url = 'note/update.do';
	var note = $('#input_note_title').data('note');
	var data = {noteId:note.id};
	var modified = false;
	var title = $('#input_note_title').val();
	if(title && title!=note.title){
		data.title = title;
		modified = true;
	}
	var body = um.getContent();
	if(body && body != note.body ){
		data.body = body;
		modified = true;
	}
	if(modified){
		$('#save_note').attr('disabled','disabled')
			.html('保存中...');
		
		$.post(url, data, function(result){
			setTimeout(function(){
				$('#save_note').removeAttr('disabled')
					.html('保存笔记');
			},1000);
			if(result.state == 0){
				//console.log("Success!");
				//内存中的 note 改成新的数据
				note.title = title;
				note.body = body;
				var l = $('#pc_part_2 .checked').parent();
				$('#pc_part_2 .checked').remove()
				var li = noteTemplate.replace(
						'[title]', title);
				var a = $(li).find('a');
				a.addClass('checked');
				l.prepend(a);
			}else{
				alert(result.mesage);
			}
		});
	}
}

function loadNote(){
	var li = $(this);
	var id = li.data('noteId');
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	
	var url = 'note/get.do';
	var data = {noteId:id};
	
	$.getJSON(url,data,function(result){
		if(result.state==0){
			var note = result.data;
			showNote(note);
		}else{
			alert(result.message);
		}
	});
}
function showNote(note){
	$('#input_note_title').data('note', note);
	$('#input_note_title').val(note.title);
	um.setContent(note.body);
}
function loadNotes(){
	var li = $(this);
	console.log(li);
	var id = li.data('notebookId');
	console.log(id);
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	
	var url = 'note/list.do';
	var data = {notebookId:id};
	
	//绑定笔记本ID， 用于添加笔记功能
	$('#input_note_title').data('notebookId', id);
	
	$.getJSON(url,data,function(result){
		if(result.state==0){
			var notes = result.data;
			showNotes(notes);
		}else{
			alert(result.message);
		}
	});
}
var noteTemplate = 
	'<li class="online">'+
		'<a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button></a>'+
		'<div class="note_menu" tabindex="-1">'+
			'<dl>'+
				'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
				'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
				'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
			'</dl>'+
		'</div>'+
	'</li>';
function showNotes(notes){
	var ul = $('#pc_part_2 .contacts-list');
	ul.empty();
	for(var i=0;i<notes.length;i++){
		var note = notes[i];
		var li = noteTemplate.replace('[title]',note.title);
		li = $(li).data('noteId',note.id);
		ul.append(li);
	}
}
function loadNotebooks() {
	var url = 'notebook/list.do';
	var data = {
		userId : getCookie('userId')
	};
	console.log(data);
	$.getJSON(url, data, function(result) {
		if (result.state == 0) {
			var list = result.data;
			showNotebooks(list);
		} else {
			alert(result.message);
		}
	});
}
function showNotebooks(list) {
	var ul = $('#pc_part_1 .contacts-list');
	ul.empty();
	for (var i = 0; i < list.length; i++) {
		var notebook = list[i];
		var li = notebookTemplate.replace('[name]', notebook.name);
		li = $(li).data('notebookId',notebook.id);
		ul.append(li);
	}
}
var notebookTemplate = '<li class="online">' + '<a>'
		+ '<i class="fa fa-book" title="online" rel="tooltip-bottom">'
		+ '</i>[name]</a></li>';
function demo(){
	var sum = 0;
	console.log(i);
	for(var i = 0; i<10; i+=2){
		sum += test();
	}
	console.log(sum);
}

function test(){
	var i = 5;
	return i + 8;
}
