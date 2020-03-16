package cn.wan.owl.exception;

public class BizException extends RuntimeException {

    private final transient IError error;

    private final transient Object[] errorMsgParams;

    public BizException(IError error, Throwable t) {
        super(t);
        this.error = error;
        this.errorMsgParams = new Object[]{};
    }

    public BizException(IError error) {
        super(error.getJsonStr());
        this.error = error;
        this.errorMsgParams = new Object[]{};
    }

    public BizException(IError error, Object... errorMsgParams) {
        super(error.getJsonStr());
        this.errorMsgParams = errorMsgParams;
        this.error = error;
    }

    public String getErrorJsonDesc() {
        return error.getJsonStr();
    }

    public IError getError() {
        return error;
    }

    public Object[] getErrorMsgParams() {
        return errorMsgParams;
    }

    @Override
    public String getMessage() {
        return String.format(error.getMessage(), errorMsgParams);
    }
}
