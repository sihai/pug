package com.iacrqq.pug.model;

public class JSONResultModel extends BaseModel
{
	private Boolean 	isSucceed;
	private Object 		result;
	private String 		errorMsg;
	
	public JSONResultModel(Boolean isSucceed, Object result, String errorMsg)
	{
		this.isSucceed = isSucceed;
		this.result = result;
		this.errorMsg = errorMsg;
	}
	
	public Boolean getIsSucceed()
	{
		return isSucceed;
	}
	public Object getResult()
	{
		return result;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setIsSucceed(Boolean isSucceed)
	{
		this.isSucceed = isSucceed;
	}
	public void setResult(Object result)
	{
		this.result = result;
	}
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
}
