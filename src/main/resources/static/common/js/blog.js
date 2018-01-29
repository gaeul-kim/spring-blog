$(document).ready(function () {

    $('#search-form').submit(function (e) {
        e.preventDefault();
        var _keyword = $(this).find('input[name = "keyword"]').val();
        location.href = '/search/' + _keyword;
    });

    $('.search-submit').click(function () {
        $('#search-form').trigger('submit');
    });

    $('.login-modal-trigger').click(function () {
        $.post('/saveCurrentURL');
        $('#login-modal').modal();
    });


    $('#login-modal').on('hide.bs.modal', function (e) {
        $('#modal-name').val('');
        $('#modal-password').val('');
        e.stopImmediatePropagation();

    });

    $('.login-submit').click(function (e) {
        e.preventDefault();
        var _form = $(this).closest('.login-form');
        var _hasEmptyValue = false;

        _form.find('span.error').remove();
        _form.find('input').each(function () {
            var _value = $(this).val().trim();
            if (!_value) {
                _hasEmptyValue = true;
                return;
            }
        })
        if (_hasEmptyValue) {
            _form.find('div:nth-last-child(2)').append('<span class="error">' + '로그인 정보가 올바르지 않습니다.' + '</span>');
        } else {
            _form.submit();
        }
    });

    $.fn.validate = function () {
        var _isValidate = true;

        $(this).find('input[data-validate-required="true"]').each(function () {
            var _this = $(this);
            var _type = _this.data('validate-type');
            var _val = _this.val();
            var _regExp = new RegExp();
            var _message = '';
            var _test = true;

            _this.closest('div').find('span.error').remove();

            switch (_type) {
                case 'name':
                    _regExp = /^[A-Za-z0-9_]{3,15}$/;
                    _test = _regExp.test(_val);
                    _message = (!_test) ? '3~15자의 영문, 숫자, 특수기호(_)만 가능합니다.' : '';
                    break;
                case 'password':
                    _regExp = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{5,20}$/;
                    _test = _regExp.test(_val);
                    _message = (!_test) ? '5~20자의 영문, 숫자나 특수문자가 필요합니다.' : '';
                    break;
                case 'password-confirm':
                    var _password = _this.closest('form').find('#password').val();
                    _test = _val == _password;
                    _message = (!_test) ? '비밀번호가 일치하지 않습니다.' : '';
                    break;
            }

            if (!_test) {
                _this.closest('div').append('<span class="error">' + _message + '</span>');
                _isValidate = false;
            }


        });
        return _isValidate;
    }
})