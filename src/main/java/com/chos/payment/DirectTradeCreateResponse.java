package com.chos.payment;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-11-16 
 * @since 1.0
 */
@XObject("direct_trade_create_res")
public class DirectTradeCreateResponse {

    /**
     * 
     * 
     */
    @XNode("request_token")
    private String requestToken;

    public String getRequestToken() {
        return requestToken;
    }
}