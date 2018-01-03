package com.qw.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @ClassName: QwException
 * @Description: 自定义业务异常处理类    友好提示
 * @author zhumeiqing
 * @date 2017年3月8日 下午16:22:11
 *
 */
public class QwException extends RuntimeException {
    private static final Logger logger = LogManager.getLogger(QwException.class.getName());
    private final int code;

    public int getCode() {
        return code;
    }

    public QwException(int code, String frdMessage) {
        super(frdMessage);
        this.code = code;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream s) {
        printStackTrace(new PrintWriter(s));
    }

    public void printStackTrace(PrintWriter s) {
        logger.error(super.getMessage());
    }
}