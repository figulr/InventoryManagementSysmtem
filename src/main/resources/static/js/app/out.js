var i = 0;
var barcodeList = [];

function valueChange(l){
    var newInput = $('#'+l+'-number').val();
    $('#'+l+'-number').removeAttr("value");
    $('#'+l+'-number').attr("value", newInput);
    $('#searchBarcode').focus();
    $('#searchBarcode').val('');
}

var main_out = {
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
        $('#btn-in-complete').on('click', function(){
            _this.intoOperation();
        })
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
                    url: '/api/v1/stock/check/' + barcode,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                }).done(function (response) {
                    if(response.id != null){
                        let product = {
                            "id": response.id,
                            "barcodeId": response.barcodeId,
                            "productName": response.productName,
                            "brand": response.brand,
                            "price": response.price,
                            "inStock":response.inStock
                        }
                        let row = `
                        <tr>
                            <td>${i + 1}</td>
                            <td>${product.id}</td>
                            <td id="${i + 1}-barcode">${product.barcodeId}</td>
                            <td><a th:href="'/product/detail/'+${product.barcodeId}" target='_blank'>${product.productName}</a></td>
                            <td>${product.brand}</td>
                            <td>${product.price}</td>
                            <td id="${i + 1}-inStock">${product.inStock}</td>
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
        var data = [];
        for(j=1; j< i+1; j++){
            var stockSub = Number($('#'+j+'-number').val());
            var input = {
                "barcodeId":
                    $('#' + j + '-barcode').html(),
                "inStock":
                    Number($('#' + j + '-inStock').html()) - stockAdd,
                "stockAdd":
                0,
                "stockSub":
                    stockSub,
                "name":
                document.getElementById('user').innerText
            }
            data.push(input);
        }
        // var dataList = JSON.stringify(data);
        // alert(dataList);
        $.ajax({
            type : 'POST',
            url : '/api/v1/stock/in',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(response){
            if(response.result) {
                alert('출고를 완료 했습니다.');
                background.offProtect();
                let fullToday = new Date();
                var year = fullToday.getFullYear();
                var month = fullToday.getMonth()+1;
                if(month < 10){
                    month = "0"+month;
                }
                var day = fullToday.getDate()
                if(day < 10){
                    day = "0"+day;
                }
                var today = year+"-"+month+"-"+day;
                alert(today);
                window.location.href="/product/inout-result/"+today;
            } else {
                var list = [];
                for(i=0; i<list.length; i++){
                    list.push(response.list[i]);
                }
                alert("출고에 실패한 물품이 있습니다."+list);
            }
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
};

main_out.init();