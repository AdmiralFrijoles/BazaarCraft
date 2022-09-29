package com.crypticelement.bazaarcraft.common.content.trade;
public class TradeCheckResult {
    public static final TradeCheckResult SUCCESS = new TradeCheckResult(true, null);
    public static final TradeCheckResult ERROR = new TradeCheckResult(false, null);

    private final boolean success;
    private final String errorId;

    private TradeCheckResult(boolean success, String errorId) {
        this.success = success;
        this.errorId = errorId;
    }

    public TradeCheckResult(String errorId) {
        this.success = false;
        this.errorId = errorId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorId() {
        return errorId;
    }
}
