var i = 0;
var barcodeList = [];

function valueChange(l){
    var newInput = $('#'+l+'-number').val();
    $('#'+l+'-number').removeAttr("value");
    $('#'+l+'-number').attr("value", newInput);
    $('#searchBarcode').focus();
    $('#searchBarcode').val('');
}

// $('#1-number').on("change keyup paste", function() {
//     var currentVal = $('#1-number').val();
//     if(currentVal != barcodeChekced.barcode) {
//         $('#btn-barcode-check').removeAttr("disabled");
//         if($('p[id = checked-msg]').length>0 == true && barcodeChekced == true) {
//             $('#checked-msg').remove("#checked-msg");
//         }
//     } else {
//         $('#btn-barcode-check').attr("disabled", "disabled");
//     }
// });
//
// $("#1-number").on("propertychange change keyup paste input", function() {
//     var currentVal = $(this).val();
//     if(currentVal == oldVal) {
//         return;
//     }
//
//     oldVal = currentVal;
//     alert("changed!");
// });

var main = {
    init : function () {
        var _this = this;
        $('#searchBarcode').focus();
        $('#btn-in').on('click', function(){
            var barcode = $('#searchBarcode').val();
            if(Number(barcode) == 0 | !barcode){
                alert("바코드를 입력해주세요.");
            } else{
                _this.intoListing(barcode);
            }
        });
        $('#searchBarcode').on('keyup', function(key){
            if(key.keyCode == 13) {
                var barcode = $('#searchBarcode').val();
                if(Number(barcode) == 0 | !barcode){
                    alert("바코드를 입력해주세요.");
                    $('#searchBarcode').val('');
                    $('#searchBarcode').focus();
                } else{
                    _this.intoListing(barcode);
                }
            }
        });
        // $('#btn-in-complete').on('click', function(){
        //     _this.intoOperation();
        // })
    },

    intoListing : function(barcode) {
        var checked = false;
        if( i > 0) {
            for (j = 0; j < barcodeList.length; j++) {
                    if(barcode==barcodeList[j]){
                        k = j+1;
                        $('#'+k+'-number').removeAttr("onchange");
                        let newInput = String(Number($('#'+k+'-number').val())+1);
                        $('#'+k+'-number').attr("value", newInput).val(newInput);
                        $('#'+k+'-number').attr("onchange","valueChange("+k+")");
                        checked = true;
                        $('#searchBarcode').val('');
                    } else {
                        continue;
                    }
            }
        }
        if (!checked) {
                barcodeList.push(barcode);
                $.ajax({
                    type: 'GET',
                    url: '/api/v1/in/' + barcode,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                }).done(function (response) {
                    if(response.id != null){
                        let product = {
                            "id": response.id,
                            "barcodeId": response.barcodeId,
                            "productName": response.productName,
                            "brand": response.brand,
                            "price": response.price
                        }
                        let row = `
                        <tr>
                            <td>${i + 1}</td>
                            <td>${product.id}</td>
                            <td id="${i + 1}-barcode">${product.barcodeId}</td>
                            <td><a th:href="'/product/detail/'+${product.barcodeId}" target='_blank'>${product.productName}</a></td>
                            <td>${product.brand}</td>
                            <td>${product.price}</td>
                            <td>
                                <input type="number" id="${i + 1}-number" value="1" min="1" onchange="valueChange(${i + 1})">
                            </td>
                        </tr>
                        `
                        $('.table').append(row);
                        i++;
                        $('#searchBarcode').val('');
                    } else {
                        alert("바코드를 다시 입력해주세요.");
                        $('#searchBarcode').val('');
                    }
                }).fail(function (){
                        alert("바코드를 다시 입력해주세요.");
                        $('#searchBarcode').val('');
                })
            }
    },

    intoOperation : function (){
        // var data = [
        //     "barcodeId":$('#searchBarcode').val(),
        //     ""
        // ];
        $.ajax({
            type : 'GET',
            url : '/api/v1/in/'+barcode,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(response){
            alert('제품정보를 불러왔습니다.');
            alert(response);
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
};

main.init();