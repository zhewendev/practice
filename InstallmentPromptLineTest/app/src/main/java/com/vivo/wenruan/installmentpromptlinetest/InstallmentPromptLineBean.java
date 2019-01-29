package com.vivo.wenruan.installmentpromptlinetest;

public class InstallmentPromptLineBean {
    /**
     * 分期节点下标名
     */
    private String name;
    /**
     * 分期节点标签状态
     */
    private Enum status;
    /**
     * 节点线长度
     */
    private int lineLength;
    /**
     *  是否存在提示框
     */
    private boolean isShowSign;
    /**
     * 提示框文本内容
     */
    private String signContent;

    public InstallmentPromptLineBean(String name, Enum status, int lineLength, boolean isShowSign, String signContent) {
        this.name = name;
        this.status = status;
        this.lineLength = lineLength;
        this.isShowSign = isShowSign;
        this.signContent = signContent;
    }

    public enum StepStatus {
        GENERAL,UNDERWAY,FAILED
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public void setIsShowSign(boolean isShowSign) {
        this.isShowSign = isShowSign;
    }

    public boolean getIsShowSign() {
        return isShowSign;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent;
    }

    public String getSignContent() {
        return signContent;
    }
}
