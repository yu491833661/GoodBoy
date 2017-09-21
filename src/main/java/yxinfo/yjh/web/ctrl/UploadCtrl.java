package yxinfo.yjh.web.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import yxinfo.yjh.web.dto.RetUploadMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/9/14.
 */
@Controller
public class UploadCtrl {

    /**
     * 文件上传
     *
     * @param files
     * @param token
     * @param code
     * @param upType
     * @param callbackUrl
     * @param request
     * @param response
     * @throws
     */
    @RequestMapping( value = "upload", method = RequestMethod.POST )
    @ResponseBody
    public List<RetUploadMsg> upload( @RequestParam MultipartFile[] files, @RequestParam String token, @RequestParam String code,
                                      String upType, String callbackUrl, HttpServletRequest request, HttpServletResponse response ) throws Exception {

        List<RetUploadMsg> retList = new ArrayList<RetUploadMsg>();

        try {


        } catch ( Exception e ) {

        }
        return retList;
    }
}
