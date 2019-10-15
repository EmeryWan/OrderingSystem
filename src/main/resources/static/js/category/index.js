;

const food_cat_ops = {

    init: function () {
        this.eventBind();
    },

    eventBind: function () {
        let that = this;

        $(".wrap_search select[name=hasDelete]").change(function () {
            $(".wrap_search").submit();
        });

        $(".remove").click(function () {
            that.delete_ops("remove", $(this).attr("data"));
        });

        $(".recover").click(function () {
            that.recover_ops("recover", $(this).attr("data"));
        });
    },

    delete_ops: function (act, id) {
        let callback = {
            "cancel": null,

            "ok": function () {
                $.ajax({
                    url: common_ops.buildUrl("/sell/seller/category/" + id),
                    type: "delete",
                    dataType: "json",
                    success: function (res) {
                        let callback = null;
                        if (res.code === 0) {
                            callback = function () {
                                window.location.href = window.location.href;
                            }
                        }
                        common_ops.alert(res.msg, callback);
                    }
                })
            }
        };
        common_ops.confirm("确定删除？", callback);
    },

    recover_ops: function (act, id) {
        let callback = {
            "cancel": null,

            "ok": function () {
                $.ajax({
                    url: common_ops.buildUrl("/sell/seller/category/" + id),
                    type: "put",
                    dataType: "json",
                    success: function (res) {
                        if (res.code === 0) {
                            callback = function () {
                                window.location.href = window.location.href;
                            }
                        }
                        common_ops.alert(res.msg, callback);
                    }
                })
            }
        };
        common_ops.confirm("确定恢复？", callback);
    }

};


$(document).ready(function () {
    food_cat_ops.init();
});