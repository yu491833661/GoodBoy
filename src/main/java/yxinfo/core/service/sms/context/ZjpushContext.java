package yxinfo.core.service.sms.context;

/**
 * 普讯短信平台参数
 * Created by dy on 2016/8/9.
 */
public interface ZjpushContext {

    String USR = "7756";
    String PWD = "yxzj@7756jk";
    String EXTDSRC_ID = "123";

    String DATA_TEM = "usr={0}&pwd={1}&mobile={2}&sms={3}&extdsrcid={4}";
    String URL = "http://202.91.244.252/qd/SMSSendYD";
}
