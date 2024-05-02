package com.gameServer.common.protocol.gameMain;

import com.zfoo.protocol.anno.Protocol;

/**
 * 我们打开 GameMain UI 界面得时候发送协议
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/24 23 53
 */
@Protocol(id = 1051)
public class OpenGameMainUIPanelRequest {
    /**
     * 打开界面名
     */
    private String panelPath;
    /**
     * 内嵌协议号 希望 能返回那些协议
     */
    private String protocolStr;

    public String getPanelPath() {
        return panelPath;
    }

    public void setPanelPath(String panelPath) {
        this.panelPath = panelPath;
    }

    public String getProtocolStr() {
        return protocolStr;
    }

    public void setProtocolStr(String protocolStr) {
        this.protocolStr = protocolStr;
    }

    public static OpenGameMainUIPanelRequest valueOf() {
        return new OpenGameMainUIPanelRequest();
    }
}