package yxinfo.core.service.ou;

import java.util.List;

/**
 * Created by dy on 2017/7/6.
 */
public interface AppTerminalService {

    /**
     * 根据终端获取应用
     *
     * @param terminal
     * @return
     */
    List<String> getAppByTerminal( String terminal ); // TODO cache

    /**
     * 根据应用获取终端
     *
     * @param app
     * @return
     */
    List<String> getTerminalByApp( String app ); // TODO cache
}
