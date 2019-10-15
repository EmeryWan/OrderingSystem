;

const category_set_ops = {

    init: function () {
        this.eventBind();
        let categoryId = $(".wrap_category_set input[name=categoryId]").val();
        console.log(categoryId);
    },

    eventBind: function () {
        $(".wrap_category_set .save").click(function () {
            let save_btn = $(this);

            if (save_btn.hasClass("disabled")) {
                common_ops.alert("正在处理！请不要重复提交");
                return;
            }

            let type_target = $(".wrap_category_set input[name=categoryType]");
            let name_target = $(".wrap_category_set input[name=categoryName]");

            let categoryType = type_target.val();
            let categoryName = name_target.val();
            // undefined
            let categoryId = $(".wrap_category_set input[name=categoryId]").val();

            if (parseInt(categoryType) < 1) {
                common_ops.tip("请输入符合规范的编号", type_target);
                return;
            }

            if (categoryName.length < 1) {
                common_ops.tip("请输入符合规范的名称", name_target);
                return;
            }

            save_btn.addClass("disabled");

            // 添加
            if (categoryId === undefined) {
                let data = {
                    "categoryType": categoryType,
                    "categoryName": categoryName
                };

                $.ajax({
                    url: "/sell/seller/category",
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
                    "categoryId": categoryId,
                    "categoryType": categoryType,
                    "categoryName": categoryName
                };

                $.ajax({
                    url: "/sell/seller/category",
                    type: "put",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(data),
                    dataType: "json",
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
    category_set_ops.init();
});