var i;
var barcodeList;
var countedProduct;
var countedHidden = 0;

function valueChange(l) {
    let inStock = Number($('#' + l + '-inStock').html());
    let newInput = Number($('#' + l + '-number').val());
    $('#' + l + '-number').removeAttr("value");
    if (inStock >= newInput) {
        if (newInput == 0) {
            alert("최소한 한 개이상 입력해야 합니다.")
            $('#' + l + '-number').attr("value", "1").val("1");
        }
        $('#' + l + '-number').attr("value", String(newInput));
    } else {
        if ($('#mode').html() == 'out') {
            $('#' + l + '-number').attr("value", String(inStock)).val(inStock);
            alert("재고가 부족합니다.");
        }
    }
    $('#searchBarcode').val('');
    $('#searchBarcode').focus();
}

function deleteRow(l) {
    $('#' + l + '-row').attr("hidden", true);
    $('#' + l + '-number').removeAttr("onchange");
    $('#' + l + '-number').removeAttr("min");
    $('#' + l + '-number').attr("value", "0").val("0");
    countedHidden++;
    $('#searchBarcode').val('');
    $('#searchBarcode').focus();
}


var main = {
    init: function () {
        i = 0;
        barcodeList = [];
        countedProduct = 0;
        var _this = this;
        $('#searchBarcode').focus();
        $('#btn-in').on('click', function () {
            var mode = $('#mode').val();
            var barcode = Number($('#searchBarcode').val());
            if (barcode == 0 | !barcode) {
                alert("바코드를 입력해주세요.");
            } else {
                if ($('#mode').html() == 'in') {
                    _this.intoListingForIn(barcode);
                } else {
                    _this.intoListingForOut(barcode);
                }
            }
        });
        $('#searchBarcode').on('keyup', function (key) {
            if (key.keyCode == 13) {
                var barcode = $('#searchBarcode').val();
                if (Number(barcode) == 0 | !barcode) {
                    alert("바코드를 입력해주세요.");
                    $('#searchBarcode').val('');
                    $('#searchBarcode').focus();
                } else {
                    if ($('#mode').html() == 'in') {
                        _this.intoListingForIn(barcode);
                    } else {
                        _this.intoListingForOut(barcode);
                    }
                }
            }
        });
        $('#btn-in-complete').on('click', function () {
            if (countedProduct - countedHidden > 0) {
                _this.intoOperation();
            } else {
                alert("입고할 상품이 없습니다.");
            }
        });
        $('#btn-out-complete').on('click', function () {
            if (countedProduct - countedHidden > 0) {
                _this.outtoOperation();
            } else {
                alert("출고할 상품이 없습니다.");
            }
        });
    },

    intoListingForIn: function (barcode) {
        var checked = false;
        if (i > 0) {
            for (j = 0; j < barcodeList.length; j++) {
                if (barcode == barcodeList[j]) {
                    k = j + 1;
                    $('#' + k + '-number').removeAttr("onchange");
                    let newInput = String(Number($('#' + k + '-number').val()) + 1);
                    $('#' + k + '-number').attr("value", String(newInput - 1)).val(newInput);
                    $('#' + k + '-number').attr("onchange", "valueChange(" + k + ")");
                    if ($('#' + k + '-row').attr("hidden") == "hidden") {
                        $('#' + k + '-number').attr("min", "1");
                        $('#' + k + '-row').removeAttr("hidden");
                        countedHidden = countedHidden - 1;
                    }
                    $('#searchBarcode').val('');
                    checked = true;
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
                if (response.id != null) {
                    let product = {
                        "id": response.id,
                        "barcodeId": response.barcodeId,
                        "productName": response.productName,
                        "brand": response.brand,
                        "price": response.price,
                        "inStock": response.inStock
                    }
                    let row = `
                        <tr id="${i + 1}-row">
                            <td>
                                ${i + 1}
                            </td>
                            <td hidden>${product.id}</td>
                            <td id="${i + 1}-barcode">${product.barcodeId}</td>
                            <td><a th:href="/product/detail/+${product.barcodeId}" target='_blank'>${product.productName}</a></td>
                            <td>${product.brand}</td>
                            <td>${product.price}</td>
                            <td id="${i + 1}-inStock">${product.inStock}</td>
                            <td>
                                <input type="number" id="${i + 1}-number" value="1" min="1" onchange="valueChange(${i + 1})">
                                <input type="button" id="${i + 1}-delete" style="width:100%; height:50%;" onclick="deleteRow(${i + 1})" value="삭제">
                            </td>
                        </tr>
                        `
                    $('.table').append(row);
                    i++;
                    countedProduct++;
                    $('#searchBarcode').val('');
                } else {
                    alert("등록되어 있지 않은 상품입니다.");
                    $('#searchBarcode').val('');
                }
            }).fail(function () {
                alert("다시 시도해 주십시오.");
                $('#searchBarcode').val('');
            })
        }
    },

    intoListingForOut: function (barcode) {
        var checked = false;
        if (i > 0) {
            for (j = 0; j < barcodeList.length; j++) {
                if (barcode == barcodeList[j]) {
                    k = j + 1;
                    let inStock = Number($('#' + k + '-inStock').html());
                    let number = Number($('#' + k + '-number').val());
                    if (inStock > number) {
                        $('#' + k + '-number').removeAttr("onchange");
                        let newInput = String(number + 1);
                        $('#' + k + '-number').attr("value", String(newInput - 1)).val(newInput);
                        $('#' + k + '-number').attr("onchange", "valueChange(" + k + ")");
                        if ($('#' + k + '-row').attr("hidden") == "hidden") {
                            $('#' + k + '-number').attr("min", "1");
                            $('#' + k + '-row').removeAttr("hidden");
                            countedHidden = countedHidden - 1;
                        }
                        $('#searchBarcode').val('');
                    } else {
                        alert("재고가 부족합니다.");
                        $('#searchBarcode').val('');
                    }
                    checked = true;
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
                if (response.id != null) {
                    let product = {
                        "id": response.id,
                        "barcodeId": response.barcodeId,
                        "productName": response.productName,
                        "brand": response.brand,
                        "price": response.price,
                        "inStock": response.inStock
                    }
                    if (product.inStock > 0) {
                        let row = `
                        <tr id="${i + 1}-row">
                            <td>${i + 1}</td>
                            <td hidden>${product.id}</tdhidden>
                            <td id="${i + 1}-barcode">${product.barcodeId}</td>
                            <td><a th:href="'/product/detail/'+${product.barcodeId}" target='_blank'>${product.productName}</a></td>
                            <td>${product.brand}</td>
                            <td>${product.price}</td>
                            <td id="${i + 1}-inStock">${product.inStock}</td>
                            <td>
                                <input type="number" id="${i + 1}-number" value="1" min="1" onchange="valueChange(${i + 1})">
                                <input type="button" id="${i + 1}-delete" style="width:100%; height:50%;" onclick="deleteRow(${i + 1})" value="삭제">
                            </td>
                        </tr>
                        `
                        $('.table').append(row);
                        i++;
                        countedProduct++;
                        $('#searchBarcode').val('');
                    } else {
                        alert("재고가 없는 제품입니다.");
                        $('#searchBarcode').val('');
                    }
                } else {
                    alert("등록되어 있지 않은 상품입니다.");
                    $('#searchBarcode').val('');
                }
            }).fail(function () {
                alert("다시 시도해 주십시오.");
                $('#searchBarcode').val('');
            })
        }
    },

    intoOperation: function () {
        if (confirm("입고를 진행하시겠습니까?")) {
            var data = [];
            for (j = 1; j < i + 1; j++) {
                var stockAdd = Number($('#' + j + '-number').val());
                if (stockAdd > 0) {
                    var input = {
                        "barcodeId":
                            $('#' + j + '-barcode').html(),
                        "inStock":
                            Number($('#' + j + '-inStock').html()) + stockAdd,
                        "stockAdd":
                        stockAdd,
                        "stockSub":
                            0,
                        "name":
                        document.getElementById('user').innerText
                    }
                    data.push(input);
                }
            }
            // var dataList = JSON.stringify(data);
            // alert(dataList);
            $.ajax({
                type: 'POST',
                url: '/api/v1/stock/in',
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (response) {
                if (response.result) {
                    alert('입고를 완료 했습니다.');
                    background.offProtect();
                    let fullToday = new Date();
                    var year = fullToday.getFullYear();
                    var month = fullToday.getMonth() + 1;
                    if (month < 10) {
                        month = "0" + month;
                    }
                    var day = fullToday.getDate()
                    if (day < 10) {
                        day = "0" + day;
                    }
                    var today = year + "-" + month + "-" + day;
                    window.location.href = "/product/inout-result/" + today;
                } else {
                    var list = [];
                    for (i = 0; i < list.length; i++) {
                        list.push(response.list[i]);
                    }
                    alert("입고에 실패한 물품이 있습니다." + list);
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },

    outtoOperation: function () {
        if (confirm("출고를 진행하시겠습니까?")) {
            var data = [];
            for (j = 1; j < i + 1; j++) {
                var stockSub = Number($('#' + j + '-number').val());
                if (stockSub > 0) {
                    var input = {
                        "barcodeId":
                            $('#' + j + '-barcode').html(),
                        "inStock":
                            Number($('#' + j + '-inStock').html()) - stockSub,
                        "stockAdd":
                            0,
                        "stockSub":
                        stockSub,
                        "name":
                        document.getElementById('user').innerText
                    }
                    data.push(input);
                }
            }
            // var dataList = JSON.stringify(data);
            // alert(dataList);
            $.ajax({
                type: 'POST',
                url: '/api/v1/stock/in',
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (response) {
                if (response.result) {
                    alert('출고를 완료 했습니다.');
                    background.offProtect();
                    let fullToday = new Date();
                    var year = fullToday.getFullYear();
                    var month = fullToday.getMonth() + 1;
                    if (month < 10) {
                        month = "0" + month;
                    }
                    var day = fullToday.getDate()
                    if (day < 10) {
                        day = "0" + day;
                    }
                    var today = year + "-" + month + "-" + day;
                    window.location.href = "/product/inout-result/" + today;
                } else {
                    var list = [];
                    for (i = 0; i < list.length; i++) {
                        list.push(response.list[i]);
                    }
                    alert("출고에 실패한 물품이 있습니다." + list);
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }
};

main.init();