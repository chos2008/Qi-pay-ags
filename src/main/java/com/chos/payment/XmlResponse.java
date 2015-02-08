package com.chos.payment;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-11-14 
 * @since 1.0
 */
@XObject("err")
public class XmlResponse {
    /**
     * ��������
     */
    @XNode("code")
    private String code;

    /**
     * �Ӵ�����
     */
    @XNode("sub_code")
    private String subCode;

    /**
     * ������Ϣ
     */
    @XNode("msg")
    private String msg;

    /**
     * ��������
     */
    @XNode("detail")
    private String detail;

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return Returns the subCode.
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * @param subCode The subCode to set.
     */
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    /**
     * @return Returns the msg.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg The msg to set.
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return Returns the detail.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail The detail to set.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}