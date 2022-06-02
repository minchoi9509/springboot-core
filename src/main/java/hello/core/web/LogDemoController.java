package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        // 가짜 프록시 객체를 생성해서 객체 대신 등록되어서 사용 할 수 있음
        // 스프링 컨테이너에 myLogger라는 이름으로 진짜 대신.. 진짜 가짜 프록시 객체를 등록함 ㅎㅎ;;
        // 가짜는 request scope랑 관계 없이 그냥 싱글톤으로 동작하지.. 신기하다..
        String requestURL = request.getRequestURL().toString();
        // MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
