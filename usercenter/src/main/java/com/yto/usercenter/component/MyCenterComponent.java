package com.yto.usercenter.component;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.yto.usercenter.fragment.MyCenterFragment;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/23 16:46
 * desc   :
 */
public class MyCenterComponent implements IComponent {
    @Override
    public String getName() {
        return "MyCenter";
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();//获取调用方设置的ActionName
        switch (actionName){
            case "getMyCenterFragment":
                CCResult result = new CCResult();
                result.addData("fragment",new MyCenterFragment());
                CC.sendCCResult(cc.getCallId(),result);
                return true;
            default:
                //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
                CC.sendCCResult(cc.getCallId(),CCResult.errorUnsupportedActionName());
                return false;
        }

    }
}
