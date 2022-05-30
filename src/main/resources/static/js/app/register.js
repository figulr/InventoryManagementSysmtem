var barcode = '';
var checked = '';
var barcodeChekced = {
    barcode : barcode,
    checked : checked,
}

$('#barcodeId').on("change keyup paste", function() {
    var currentVal = $('#barcodeId').val();
    console.log(currentVal);
    console.log(barcodeChekced.barcode);
    if(currentVal != barcodeChekced.barcode) {
        $('#btn-barcode-check').removeAttr("disabled");
    } else {
        $('#btn-barcode-check').attr("disabled", "disabled");
        // document.getElementById('#btn-barcode-check').setAttribute('disabled', 'disabled');
    }
});


var main = {
    init : function () {
        var _this = this;
        $('#btn-register').on('click', function(){
            _this.register();
        });
        $('#btn-barcode-check').on('click', function(){
            _this.barcodeCheck();
        })
    },

    register : function() {
        var barcode = $('#barcodeId').val();

        if(barcode == barcodeChekced.barcode && barcodeChekced.checked) {
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
                if(result) {
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

    barcodeCheck : function(){
        var barcode = $('#barcodeId').val();
        alert(barcode);
        $.ajax({
            type : 'GET',
            url : '/api/v1/register/check/'+barcode,
            dataType : 'json',
            success : function (result) {
                alert(result);
                if(result){
                    barcodeChekced.barcode = barcode;
                    barcodeChekced.checked = result;
                    alert("등록 가능한 바코드입니다.")
                    $('#btn-barcode-check').attr('disabled', 'disabled');
                } else {
                    alert("이미 등록되어 있는 바코드입니다.");
                }
            },
            error : function (error){
                alert(error);
            }
        });
    }
};

main.init();