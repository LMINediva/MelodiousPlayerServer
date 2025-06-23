package com.melodiousplayer.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义用户账号异常
 *
 * @author Mike
 * @date 2025/06/22
 */
public class UserCountLockException extends AuthenticationException {

    public UserCountLockException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserCountLockException(String msg) {
        super(msg);
    }

}
