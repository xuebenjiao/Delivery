package com.yto.scan.component;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.yto.scan.fragment.HeadLineNewsFragment;
import com.yto.scan.fragment.MainScanFragment;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/23 14:52
 * desc   :
 */
public class ScanComponent implements IComponent {
    @Override
    public String getName() {
        return "Scan";
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        switch (actionName){
            case "getMainScanFragment":
                CCResult result = new CCResult();
                result.addData("fragment",new MainScanFragment());
                CC.sendCCResult(cc.getCallId(),result);
                return true;
            default:
                //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
                CC.sendCCResult(cc.getCallId(),CCResult.errorUnsupportedActionName());
                return false;
        }
    }
}
