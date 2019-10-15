;
const product_index_ops = {
    init: function () {
        this.eventBind();
    },

    eventBind: function () {
        let that = this;
        // 下架
        $(".off").click(function () {
            that.ops("off", $(this).attr("data"))
        });
        // 上架
        $(".on").click(function () {
            that.ops("on", $(this).attr("data"))
        });

        $(".wrap_search .search").click(function () {
            $(".wrap_search").submit();
        });
    },

    ops: function (act, id) {
        let callback = {
            'cancel': null,

            'ok': function () {
                $.ajax({
                    url: "/sell/seller/product/" + act + "/" + id,
                    type: 'PUT',
                    dataType: 'json',
                    success: function (res) {
                        let callback = null;
                        if (res.code === 0) {
                            callback = function () {
                                window.location.href = window.location.href;
                            }
                        }
                        common_ops.alert(res.msg, callback);
                    }
                });
            }
        };
        common_ops.confirm((act === "off") ? "确定下架？" : "确定上架？", callback);
    }
};

$(document).ready( function(){
    product_index_ops.init();
});