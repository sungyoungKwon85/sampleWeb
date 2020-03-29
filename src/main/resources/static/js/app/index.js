// var main 를 만든 이유?
// index.mustache에 index.js 외에 a.js가 푸가 되었고, a.js에도 init과 save가 있다면?
// 덮어 씌어져 버림
// 개발할때 메서드명이 있는지 일일이 확인할 수가 없음

var index = {
  init : function () {
    var _this = this;
    $('#btn-save').on('click', function () {
      _this.save();
    });
    $('#btn-update').on('click', function () {
      _this.update();
    });
    $('#btn-delete').on('click', function () {
      _this.delete();
    });
  },
  save : function () {
    var data = {
      title: $('#title').val(),
      author: $('#author').val(),
      content: $('#content').val()
    };

    $.ajax({
      type: 'POST',
      url: '/api/v1/posts',
      dataType: 'json',
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function () {
      alert('Added the posts');
      window.location.href = '/';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    })
  },
  update : function () {
    var data = {
      title: $('#title').val(),
      author: $('#author').val(),
      content: $('#content').val()
    };

    var id = $('#id').val();

    $.ajax({
      type: 'PUT',
      url: '/api/v1/posts/' + id,
      dataType: 'json',
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function () {
      alert('Updated the posts');
      window.location.href = '/';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    })
  },
  delete : function () {
    var id = $('#id').val();

    $.ajax({
      type: 'DELETE',
      url: '/api/v1/posts/' + id,
      dataType: 'json',
      contentType: 'application/json; charset=utf-8'
    }).done(function () {
      alert('Deleted the posts');
      window.location.href = '/';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    })
  }
};
index.init();