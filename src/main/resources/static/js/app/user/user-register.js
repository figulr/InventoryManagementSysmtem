var email = '';
var checked = '';
var emailChecked = {
    email : email,
    checked : checked,
}

$('#email').on("change keyup paste", function() {
    var currentVal = $('#email').val();
    if(currentVal != emailChecked.email) {
        $('#btn-email-check').removeAttr("disabled");
        if($('p[id = checked-msg]').length>0 == true && barcodeChekced.checked == true) {
            $('#checked-msg').remove("#checked-msg");
        }
    } else {
        $('#btn-barcode-check').attr("disabled", "disabled");
    }
})

$('#password').on("change keyup paste", function(){
    var msg = document.getElementById('pwd-safefy-msg');
    var password = $('#password').val();
    var strongRegex = new RegExp("^(?=.{10,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
    var mediumRegex = new RegExp("^(?=.{9,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
    var enoughRegex = new RegExp("(?=.{8,}).*", "g");
    // if (password.length == 0) {
    //     // msg.innerHTML = 'Type Password';
    // } else
    if (false == enoughRegex.test(password)) {
        msg.innerHTML = '<span style="font-size: 0.8rem">알파벳 대소문자와 특수문자 모두 포함 8자 이상이어야 합니다.</span>';
    } else if (strongRegex.test(password)) {
        msg.innerHTML = '비밀번호 안정도 :<span style="color:green">높음</span>';
    } else if (mediumRegex.test(password)) {
        msg.innerHTML = '비밀번호 안정도 :<span style="color:orange">중간</span>';
    } else {
        msg.innerHTML = '비밀번호 안정도 : <span style="color:red">낮음</span>';
    }
})
$('#rePassword').on("change keyup paste", function(){
    var password = $('#password').val();
    var currentRePassword = $('#rePassword').val();
    if(currentRePassword == password){
        $('#btn-register').removeAttr('disabled').removeClass('btn-secondary').addClass('btn-primary');
    } else {
        $('#btn-register').attr("disabled","disblaed").removeClass('btn-primary').addClass('btn-secondary');
    }
})

var main = {
    init : function () {
        var _this = this;
        $('#btn-register').on('click', function(){
            _this.register();
        });
        $('#btn-email-check').on('click', function(){
            _this.emailCheck();
        });
        $('#btn-email-reset').on('click', function(){
            _this.emailReset();
        });
    },

    register : function() {
        var email = $('#email').val();
        var pwd = $('#password').val();
        var rePwd = $('#rePassword').val();
        var checked = (emailChecked.email)&&(emailChecked.checked);
        if(checked && (pwd == rePwd)) {
            console.log("진입했다.")
            var data = {
                email: email,
                name: $('#name').val(),
                password: pwd
            };
            console.log("ajax 시작")
            $.ajax({
                type: 'POST',
                url: '/api/v0/user/register',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                if(result) {
                    alert('회원 등록이 완료되었습니다.');
                    window.location.href = '/auth/login';
                // @@@ 로그인 페이지로 이동 수정입
                } else {
                    alert('회원 정보를 확인해 주세요.');
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
            console.log("ajax 종료")
        } else {
            alert("비밀번호를 다시 입력해 주십시오.");
        }
    },

    emailCheck : function(){
        var email = $('#email').val();
        if(!email.includes("@")){
            alert("Email을 다시 입력해 주세요.");
            return false;
        } else {
            var emailDomain = email.split("@")[1];
            if(!emailDomain.includes(".")){
                alert("Email을 다시 입력해 주세요.");
                return false;
            } else {
                $.ajax({
                    type : 'GET',
                    url : '/api/v0/user/check/'+email,
                    dataType : 'json',
                    success : function (result) {
                        if(result){
                            emailChecked.email = email;
                            emailChecked.checked = result;
                            alert("등록 가능한 Email 주소입니다.");
                            $('#btn-email-check').attr('disabled', 'disabled').removeClass('btn-primary').addClass('btn-secondary');
                            $('#btn-email-reset').removeAttr("disabled").removeClass('btn-secondary').addClass('btn-primary');
                            $('#email').attr('disabled','disabled');
                            $('#name').removeAttr("disabled");
                            $('#password').removeAttr("disabled");
                            $('#rePassword').removeAttr("disabled");
                            $('#checked-msg').removeAttr('hidden');
                            $('#unchecked-msg').attr('hidden', true);

                            // $('.barcode-group').append("<div class=\"form-group\"><button id=\"btn-barcode-reset\"class=\"btn btn-info\"type=\"button\">새로운 바코드 입력하기</button></div>");
                        } else {
                            alert("이미 등록되어 있는 Email 주소입니다.");
                        }
                    },
                    error : function (){
                        alert('다시 시도해 주십시오.');
                    }
                });
            }
        }

    },
    emailReset : function(){
        alert("입력한 Email 주소를 삭제하시겠습니까?");
        emailChecked.email = '';
        emailChecked.checked = false;
        $('#email').removeAttr('disabled');
        $('#email').empty();
        $('#unchecked-msg').removeAttr('hidden');
        $('#checked-msg').attr('hidden', true);
        $('#name').attr('disabled','disabled');
        $('#name').empty();
        $('#password').empty();
        $('#rePassword').empty();
        $('#password').attr('disabled','disabled');
        $('#rePassword').attr('disabled','disabled');
        $('#btn-email-check').removeAttr('disabled').removeClass('btn-secondary').addClass('btn-primary');
        $('#btn-email-reset').attr('disabled', 'disabled').removeClass('btn-primary').addClass('btn-secondary');
        $('#btn-register').attr('disabled','disabled').removeClass('btn-primary').addClass('btn-secondary');

    }
};

main.init();