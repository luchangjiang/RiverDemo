package com.river.postgres.exception;

import com.river.postgres.util.R;

public class ResultException extends RuntimeException {

    private R resultModel;

    public ResultException(R resultModel) {
        super(resultModel.getMsg());
        this.resultModel = resultModel;
    }

    public ResultException(Throwable cause, R resultModel) {
        super(cause);
        this.resultModel = resultModel;
//        this.resultModel.setResult(cause);
    }

    public R getResultModel() {
        return resultModel;
    }
}
