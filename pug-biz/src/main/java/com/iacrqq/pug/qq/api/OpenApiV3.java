package com.iacrqq.pug.qq.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Java SDK for  OpenAPI V3   - 提供访问腾讯开放平台 OpenApiV3 的接口
 *
 * @version 3.0.0
 * @since jdk1.5
 * @author mail: zdl1016@gmail.com qq:33384782
 * @ History:
 *               3.0.0 | Zhang Dongliang | 2012-03-21 09:43:11 | initialization
 */
public class OpenApiV3
{

    /**
     * 构造函数
     *
     * @param appid 应用的ID
     * @param appkey 应用的密钥
     */
    public OpenApiV3(String appid, String appkey)
    {
        this.appid = appid;
        this.appkey = appkey;

    }
    
    /**
     * 设置OpenApi服务器的地址
     *
     * @param serverName OpenApi服务器的地址
     */
    public void setServerName(String serverName)
    {
        this.serverName = serverName;
    }

    /**
     * 执行API调用
     * 
     * @param scriptName OpenApi CGI名字
     * @param params OpenApi的参数列表
     * @param protocol HTTP请求协议 "http" / "https"
     * @return 返回服务器响应内容
     */
    public String api(String scriptName, Map<String, String> params, String protocol) throws OpensnsException
    {
        // 检查openid openkey等参数
        if (params.get("openid") == null)
        {
            throw new OpensnsException(ErrorCode.PARAMETER_EMPTY, "openid is empty");
        }
        
        if (params.get("openkey") == null)
        {
            throw new OpensnsException(ErrorCode.PARAMETER_EMPTY, "openkey is empty");
        }

        if (!isOpenid(params.get("openid")))
        {
            throw new OpensnsException(ErrorCode.PARAMETER_INVALID, "openid is invalid");
        }

        // 无需传sig,会自动生成
        params.remove("sig");

        // 添加固定参数
        params.put("appid", this.appid);

        // 请求方法
        String method = "post";
        
        // 签名密钥
        String secret = this.appkey + "&";
        
        // 计算签名
        String sig = SnsSigCheck.makeSig(method, scriptName, params, secret);
        
        params.put("sig", sig);

        StringBuilder sb = new StringBuilder(64);
        sb.append(protocol).append("://").append(this.serverName).append(scriptName);
        String url = sb.toString(); 

        // cookie
        HashMap<String, String> cookies = null;

        // 发送请求
        String resp = SnsNetwork.postRequest(url, params, cookies, protocol);

        return resp;
    }

    /**
     * 验证openid是否合法
     */
    private boolean isOpenid(String openid)
    {
        return (openid.length()==32) && openid.matches("^[0-9A-Fa-f]+$");
    }
    
    private String appid;;
    private String appkey;
    private String serverName;
}
