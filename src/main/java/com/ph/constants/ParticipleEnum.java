package com.ph.constants;



/**
 * @Description 词性数据字典英文与汉字对应
 * @Author pangh
 * @Date 2020年05月22日 9:43 上午
 * @Version v1.0.0
 */
public enum ParticipleEnum {

    VA("VA","谓词性形容词"),
    VC("VC","系动词"),
    VE("VE","存在性动词"),
    VV("VV","其他动词"),
    NR("NR","专有名词"),
    NT("NT","时间名词"),
    NN("NN","其他名词"),
    LC("LC","方位词"),
    PN("PN","代词"),
    DT("DT","限定词"),
    CD("CD","基数词"),
    OD("OD","序列词"),
    M("M","度量词"),
    AD("AD","副词"),
    P("P","介词"),
    CC("CC","并列连接词"),
    CS("CS","从属连接词"),
    DEC("DEC","“的”作为补语标记/名词化标记，如：吃的"),
    DEG("DEG","“的”作为关联标记/所有格标记，如：淡淡的花香"),
    DER("DER","“得”，如：穿得好看"),
    DEV("DEV","“地”，如：不断地提醒"),
    AS("AS","动词助词"),
    SP("SP","句末助词"),
    ETC("ETC","“等”，“等等”"),
    MSP("MSP","其他助词"),
    IJ("IJ","感叹词"),
    ON("ON","拟声词"),
    LB("LB","长“被”结构"),
    SB("SB","短“被”结构"),
    BA("BA","把字结构"),
    JJ("JJ","其他名词修饰词"),
    FW("FW","外来词"),
    PU("PU","标点");


    public String getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    private String retCode;
    private String retMessage;

    ParticipleEnum(String retCode, String retMessage){
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static ParticipleEnum forEach_CountryEnum(String index){
        ParticipleEnum[] values = ParticipleEnum.values();
        for (ParticipleEnum element: values) {
            if(element.retCode.equals(index)){
                return element;
            }
        }
        return null;
    }
}
