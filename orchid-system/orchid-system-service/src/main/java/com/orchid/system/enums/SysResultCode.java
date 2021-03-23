package com.orchid.system.enums;

import com.orchid.core.ResultCode;

public enum SysResultCode implements ResultCode {

    ORANG_ERROR(500001, "机构管理错误");

    SysResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;
    @Override
    public int code() {
        return 0;
    }

    @Override
    public String msg() {
        return null;
    }
}
