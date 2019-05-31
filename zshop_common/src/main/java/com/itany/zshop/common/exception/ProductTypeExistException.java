package com.itany.zshop.common.exception;

/**
 * Author：汤小洋
 * Date：2018-04-26 16:44
 * Description：<描述>
 */
public class ProductTypeExistException extends Exception {
    public ProductTypeExistException() {
        super();
    }

    public ProductTypeExistException(String message) {
        super(message);
    }

    public ProductTypeExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeExistException(Throwable cause) {
        super(cause);
    }
}
