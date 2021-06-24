package returnmsg;

import com.alibaba.fastjson.JSON;

public class ReturnMsg {
    private int isSuccess;
    private String error;
    private String msg;

    public ReturnMsg() {
    }

    private ReturnMsg(int isSuccess, String error, String msg) {
        this.isSuccess = isSuccess;
        this.error = error;
        this.msg = msg;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public static ReturnMsg getSuccessMsg(String msg) {
        return new ReturnMsg(1, "", msg);
    }

    public static ReturnMsg getErrorMsg(String error, String msg) {
        return new ReturnMsg(0, error, msg);
    }

    public static ReturnMsg getErrorMsg(String error) {
        return new ReturnMsg(0, error, "");
    }
}
