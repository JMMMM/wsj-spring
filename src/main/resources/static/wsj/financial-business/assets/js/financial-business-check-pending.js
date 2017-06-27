/**
 * Created by wendy-pc on 2016/7/26.
 */
Polymer({
    is: "financial-business-check-pending",
    behaviors: [OCrudBehavior, FinancialBusinessBehavior],
    properties: {
        status: {
            type: Number,
            value: 1
        }
    },
    _init: function () {
        this.reset();
        this.loadOrderNo();
        this.loadCustomerName();
    }
});
