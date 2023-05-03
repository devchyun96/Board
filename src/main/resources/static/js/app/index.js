var main = {
    init : function () {
        const _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-userUpdate').on('click', function(){
            _this.userUpdate();
        });

        $('#btn-commentSave').on('click', function () {
             _this.commentSave();
        });

        document.querySelectorAll('#btn-commentUpdate').forEach(function (item){
            item.addEventListener('click',function(){
                const form=this.closest('commentForm');
                _this.commentUpdate(form);
            });
        });
    },
    save : function () {
        const data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
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

        const data = {
            id: $('#id').val(),
            title: $('#title').val(),
            content: $('#content').val()
        };


        const check=confirm("글을 수정합니다.");
        if(check===true){
           if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
                      alert("입력되지 않았습니다.");
                      return false;
        }
        else{
        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+data.id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts/view/' + data.id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
        }
      }
    },
    delete : function () {
        const id = $('#id').val();

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
    },
    userUpdate : function() {
        const data={
            id: $('#id').val(),
            username: $('#username').val(),
            nickname: $('#nickname').val(),
            password: $('#password').val(),
            modifiedDate: $('#modifiedDate').val()
        }

        if(!data.nickname || data.nickname.trim() === "" || !data.password || data.password.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else if(!/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/.test(data.password)) {
            alert("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            $('#password').focus();
            return false;
        } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.nickname)) {
            alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
            $('#nickname').focus();
            return false;
        }
        const check= confirm("회원정보를 수정합니다.");
        if(check===true){
            $.ajax({
                type : "PUT",
                url : "/api/v1/users",
                contentType : 'application/json; charset=utf-8',
                data : JSON.stringify(data)
            }).done(function(){
                alert("수정을 완료했습니다.");
                window.location.href= "/";

            }).fail(function (error) {
               if (error.status === 500) {
                        alert("이미 사용중인 닉네임 입니다.");
                        $('#nickname').focus();
               }else{
                    alert(JSON.stringify(error));
               }
            });
        } else{
            return false;
        }
    },

    commentSave : function(){
        const data={
            postsId: $('#postsId').val(),
            comment: $('#comment').val()
        }
        if (!data.comment || data.comment.trim() === "") {
                  alert("공백 또는 입력하지 않은 부분이 있습니다.");
                  return false;
        }
        const check = confirm("등록하시겠습니까?");
        if (check === true) {
            $.ajax({
                type: 'POST',
                url: '/api/v1/posts/' + data.postsId + '/comments/',
                dataType: 'text',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글을 등록했습니다.');
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    commentUpdate : function(form){
        const data= {
            id: form.querySelector('#id').value,
            postsId: form.querySelector('#postsId').value,
            comment: form.querySelector('#commentContent').value
        }
        if(!data.comment || data.comment.trim()===""){
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        }
        const check=confirm("수정하시겠습니까?");
        if(check===true){
            $.ajax({
                type: 'PUT',
                url: '/api/v1/posts' + data.postsId + '/comments/' + data.id,
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                window.location.reload();
            }).fail(function() {
                alert(JSON.stringify(error));
            });
        }
    },

    commentDelete : function(postsId, commentId){
        const check = confirm("삭제하시겠습니까?");
        if(check===true){
            $.ajax({
                type: 'DELETE'
                url: '/api/v1/posts' + postsId + '/comments/' + commentId,
                dataType: 'JSON'
            }).done(function (){
                alert('댓글이 삭제 되었습니다.');
                window.location.reload();
            }).fail(function () {
                alert(JSON.stringify(error));
            });
        }
    }
};


main.init();