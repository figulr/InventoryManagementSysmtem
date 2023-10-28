var barcode = '';
var checked = '';
var barcodeChekced = {
    barcode: barcode,
    checked: checked,
}

$('#barcodeId').on("change keyup paste", function () {
    var currentVal = $('#barcodeId').val();
    if (currentVal != barcodeChekced.barcode) {
        $('#btn-barcode-check').removeAttr("disabled");
        if ($('p[id = checked-msg]').length > 0 == true && barcodeChekced == true) {
            $('#checked-msg').remove("#checked-msg");
        }
    } else {
        $('#btn-barcode-check').attr("disabled", "disabled");
    }
});

var main = {
    init: function () {
        var _this = this;
        $('#btn-register').on('click', function () {
            _this.register();
        });
        $('#btn-barcode-check').on('click', function () {
            _this.barcodeCheck();
        });
        $('#btn-barcode-reset').on('click', function () {
            _this.barcodeReset();
        });
    },

    register: function () {
        var barcode = $('#barcodeId').val();

        if (barcode == barcodeChekced.barcode && barcodeChekced.checked) {
            var data = {
                barcodeId: barcode,
                productName: $('#productName').val(),
                brand: $('#brand').val(),
                price: $('#price').val(),
                store: $('#store').val(),
                weight: $('#weight').val(),
                unit: $('#unit').val(),
                volumeLong: $('#volumeLong').val(),
                volumeShort: $('#volumeShort').val(),
                volumeHeight: $('#volumeHeight').val()
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/register',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                if (result) {
                    alert('제품이 등록되었습니다.');
                    window.location.href = '/product/detail/' + barcode;
                } else {
                    alert('제품 정보를 확인해 주세요.');
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        } else {
            alert("바코드 중복 확인을 해주세요.");
        }
    },
    barcodeCheck: function () {
        var barcode = $('#barcodeId').val();
        $.ajax({
            type: 'GET',
            url: '/api/v1/register/check/' + barcode,
            dataType: 'json',
            success: function (result) {
                if (result) {
                    barcodeChekced.barcode = barcode;
                    barcodeChekced.checked = result;
                    alert("등록 가능한 바코드입니다.")
                    $('#barcodeId').attr('disabled', 'disabled');
                    $('#unchecked-msg').attr('hidden', true);
                    $('#checked-msg').removeAttr('hidden');
                    $('#btn-barcode-check').attr('disabled', 'disabled').removeClass('btn-primary').addClass('btn-secondary');
                    $('#btn-barcode-reset').removeAttr("disabled").removeClass('btn-secondary').addClass('btn-primary');
                    $('#productName').removeAttr("disabled");
                    $('#brand').removeAttr("disabled");
                    $('#price').removeAttr("disabled");
                    $('#store').removeAttr("disabled");
                    $('#weight').removeAttr("disabled");
                    $('#unit').removeAttr("disabled");
                    $('#volumeShort').removeAttr("disabled");
                    $('#volumeLong').removeAttr("disabled");
                    $('#volumeHeight').removeAttr("disabled");
                    $('#btn-register').removeAttr('disabled').removeClass('btn-secondary').addClass('btn-primary');

                    // $('.barcode-group').append("<div class=\"form-group\"><button id=\"btn-barcode-reset\"class=\"btn btn-info\"type=\"button\">새로운 바코드 입력하기</button></div>");
                } else {
                    alert("이미 등록되어 있는 바코드입니다.");
                }
            },
            error: function () {
                alert('다시 시도해 주십시오.');
            }
        });
    },
    barcodeReset: function () {
        alert("입력한 바코드 정보를 삭제하시겠습니까?");
        barcodeChekced.barcode = '';
        barcodeChekced.checked = false;
        $('#barcodeId').removeAttr('disabled');
        $('#barcodeId').empty();
        $('#unchecked-msg').removeAttr('hidden');
        $('#checked-msg').attr('hidden', true);
        $('#btn-barcode-check').removeAttr('disabled').removeClass('btn-secondary').addClass('btn-primary');
        $('#btn-barcode-reset').attr('disabled', 'disabled').removeClass('btn-primary').addClass('btn-secondary');
        $('#btn-register').attr('disabled', 'disabled').removeClass('btn-primary').addClass('btn-secondary');

    }
};

main.init();