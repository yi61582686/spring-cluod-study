package com.on.util;

import java.util.HashMap;

public class ResponseObject extends HashMap{
    public static  String SUCCESS_CODE="200";
    public static String ERROR_CODE="500";
    public static String DATA_KEY = "data";
    public static String MSG_KEY = "msg";

    private ResponseObject(){

    }

    public ResponseObject set(String key, Object object){
        super.put(key,object);
        return  this;
    }

    private  static ResponseObject ok(){
        return new ResponseObject();
    }

    public static ResponseObject success(){
        return ResponseObject.ok().set("code", ResponseObject.SUCCESS_CODE).set(ResponseObject.MSG_KEY,"操作成功");
    }

    public static ResponseObject success(String msg){
        return ResponseObject.ok().set("code", ResponseObject.SUCCESS_CODE).set(ResponseObject.MSG_KEY,msg);
    }

    public static ResponseObject success(String msg, Object object){
        return ResponseObject.ok().set("code", ResponseObject.SUCCESS_CODE).set(ResponseObject.MSG_KEY,msg).set(ResponseObject.DATA_KEY,object);
    }

    public ResponseObject data(Object obj){
        return this.set("data",obj);
    }

    public static ResponseObject error(){
        return ResponseObject.ok().set(ResponseObject.MSG_KEY,"操作失败").set("code", ResponseObject.ERROR_CODE);
    }

    public static ResponseObject error(String msg){
        return ResponseObject.ok().set(ResponseObject.MSG_KEY,msg).set("code", ResponseObject.ERROR_CODE);
    }

    public static ResponseObject error(String msg, Object object){
        return ResponseObject.ok().set(ResponseObject.MSG_KEY,msg).set(ResponseObject.DATA_KEY,object).set("code", ResponseObject.ERROR_CODE);
    }

}
