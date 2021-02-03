/*
- index 객체 안에서만 function이 유효하게 하기 위해서 main = {} 선언하여 사용.
- 여러 사람이 참여하는 프로젝트에서는 중복된 함수 이름은 자주 발생하고
- 나중에 로딩된 동일함수가 function을 덮어쓸수 있기 때문에 index.js만의 유효범위를 설정한 것.
- 여러 HTTP Method 중 PostsApiController의 api에서 선언한 값과 동일하게 type을 지정해줘야 함.
  이는 Rest 규약에 맞게 설정된 것으로 REST에서는 CRUD별 HTTP Method는 아래와 같이 맵핑한다.
      1. 생성 (Create) - POST
      2. 읽기 (Read) - GET
      3. 수정 (Update) - PUT
      4. 삭제 (Delete) - DELETE
*/
var main = {
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
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id, // 어느 게시글을 수정할지 URL Path로 구분하기 위해 id를 추가
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};




main.init();