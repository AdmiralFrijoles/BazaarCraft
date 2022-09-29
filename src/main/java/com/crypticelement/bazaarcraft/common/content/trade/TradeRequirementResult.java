package com.crypticelement.bazaarcraft.common.content.trade;
public class TradeRequirementResult {
    public static final TradeRequirementResult SUCCESS = new TradeRequirementResult(true, null);
    public static final TradeRequirementResult ERROR = new TradeRequirementResult(false, null);

    private final boolean success;
    private final String errorId;

    private TradeRequirementResult(boolean success, String errorId) {
        this.success = success;
        this.errorId = errorId;
    }

    public TradeRequirementResult(String errorId) {
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
