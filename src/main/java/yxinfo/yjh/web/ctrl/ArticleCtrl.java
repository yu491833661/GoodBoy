package yxinfo.yjh.web.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import yxinfo.core.common.route.ClearAuth;
import yxinfo.yjh.service.article.ArticleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文章/提问
 *
 * @author jn
 * @date 2017/9/18 14:59
 **/
@Controller
public class ArticleCtrl {

    @Resource
    private ArticleService articleService;

    @RequestMapping("/")
    @ClearAuth
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView( "home" );

        return mav;
    }

    @RequestMapping("/articles/list/{blockId}{articleId}")
    @ClearAuth
    public ModelAndView article(@PathVariable Integer articleId){

        articleService.click( articleId );

        ModelAndView mav = new ModelAndView();

        return mav;
    }

    @RequestMapping("/blocks/{blockId}")
    @ClearAuth
    public ModelAndView list(@PathVariable Integer blockId, HttpServletRequest request){

        ModelAndView mav = new ModelAndView();

        return mav;
    }


}
