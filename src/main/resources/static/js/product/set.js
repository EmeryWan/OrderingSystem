;

const product_set_ops = {

    init: function () {
        this.eventBind();
        let categoryId = $(".wrap_product_set input[name=productId]").val();
        console.log(categoryId);
    },

    eventBind: function () {
        $(".wrap_product_set .save").click(function () {
            let save_btn = $(this);

            if (save_btn.hasClass("disabled")) {
                common_ops.alert("正在处理！请不要重复提交");
                return;
            }

            let type_target = $(".wrap_product_set select[name=categoryType]");
            let name_target = $(".wrap_product_set input[name=productName]");
            let price_target = $(".wrap_product_set input[name=productPrice]");
            let stock_target = $(".wrap_product_set input[name=productStock]");
            let description_target = $(".wrap_product_set textarea[name=productDescription]");

            let categoryType = type_target.val();
            let productName = name_target.val();
            let productPrice = price_target.val();
            let productStock = stock_target.val();
            let productDescription = description_target.val();

            console.log(categoryType);
            console.log(productName);
            console.log(productPrice);
            console.log(productStock);
            console.log(productDescription);

            // undefined
            let productId = $(".wrap_product_set input[name=productId]").val();

            if (parseInt(categoryType) <= 0) {
                common_ops.tip("请选择该商品的分类", type_target);
                return;
            }

            if (productName.length < 1) {
                common_ops.tip("请输入符合规范的名称", name_target);
                return;
            }

            if (parseFloat(productPrice) < 0) {
                common_ops.tip("请输入符合规范的价格", price_target);
                return;
            }

            if (parseInt(productStock) < 0) {
                common_ops.tip("请输入符合规范的库存", stock_target);
                return;
            }


            if (productDescription.length < 1) {
                common_ops.tip("请输入符合规范的描述", name_target);
                return;
            }

            save_btn.addClass("disabled");

            // 添加
            if (productId === undefined) {
                let data = {
                    "productName": productName,
                    "productPrice": productPrice,
                    "productStock": productStock,
                    "productDescription": productDescription,
                    // "productIcon": productIcon,
                    "categoryType": categoryType
                };

                $.ajax({
                    url: "/sell/seller/product",
                    type: "post",
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    success: function (res) {
                        save_btn.removeClass("disabled");

                        let callback = null;
                        if (res.code === 0) {
                            callback = function () {
                                window.location.href = common_ops.buildUrl("/sell/seller/category/index");
                            }
                        }
                        common_ops.alert(res.msg, callback);
                    }
                });
            } else {
                // 更新
                let data = {
                    "productId": productId,
                    "productName": productName,
                    "productPrice": productPrice,
                    "productStock": productStock,
                    "productDescription": productDescription,
                    // "productIcon": productIcon,
                    "categoryType": categoryType
                };

                $.ajax({
                    url: "/sell/seller/product",
                    type: "put",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(data),
                    dataType: "json",
                    success: function (res) {
                        save_btn.removeClass("disabled");

                        let callback = null;
                        if (res.code === 0) {
                            callback = function () {
                                window.location.href = common_ops.buildUrl("/sell/seller/product/index");
                            }
                        }
                        common_ops.alert(res.msg, callback);
                    }
                })
            }
        })
    },

    updateData: function (data, save_btn) {

    },

    saveData: function (data) {

    }
};

$(document).ready(function () {
    product_set_ops.init();
});