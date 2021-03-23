package com.orchid.system;

import com.orchid.core.ResultCode;

public enum  SysResultCode implements ResultCode {
    ORGAN_ERRPR(500001, "机构管理错误");


    private int code;
    private String msg;

    SysResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.msg;
    }
}
