package com.xuhailiang5794.ik.support.result;

import lombok.Data;

/**
 * <pre>
 * 返回结果基类
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16
 */
@Data
public class BaseResult<T> {

    /**
     * 数据结果
     */
    private T data;

    /**
     * 是否操作成功
     */
    private boolean success;
    /**
     * 回馈信息
     */
    private String msg;

    /**
     * 错误代码
     */
    private String errorCode;

    public static BaseResult success(String message) {
        BaseResult result = new BaseResult();
        result.setSuccess(true);
        result.setErrorCode(null);
        result.setMsg(message);
        return result;
    }

    public static BaseResult success() {
        return success(null);
    }

    public static BaseResult fail(String errorCode, String message) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setMsg(message);
        return result;
    }

    public static BaseResult fail() {
        return fail(null, null);
    }
}
