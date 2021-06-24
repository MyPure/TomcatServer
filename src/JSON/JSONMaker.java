package JSON;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONMaker extends JSONObject {
    public JSONMaker(){

    }

    public void setSuccess(int isSuccess){
        this.put("isSuccess",isSuccess);
    }
    public void setError(String error){
        this.put("error",error);
    }
    public void setJsonObject(JSONObject jsonObject){
        this.put("json",jsonObject);
    }
    public void setJsonArray(JSONArray jsonArray){
        this.put("json",jsonArray);
    }

}
