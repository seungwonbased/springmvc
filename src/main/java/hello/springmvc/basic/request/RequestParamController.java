package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {

	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		log.info("username={}, age={}", username, age);

		response.getWriter().write("ok");
	}

	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestParamV2(
		@RequestParam("username") String memberName,
		@RequestParam("age") int memberAge) {
		log.info("username={}, age={}", memberName, memberAge);

		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3(
		@RequestParam String username,
		@RequestParam int age) {
		log.info("username={}, age={}", username, age);

		return "ok";
	}

	// String, int, Integer 등 단순 타입이면 @RequestParam 생략 가능
	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4(String username, int age) {
		log.info("username={}, age={}", username, age);

		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(
		@RequestParam(required = true) String username,
		@RequestParam(required = false) Integer age) {
		// required가 false여도 int age에 값을 입력하지 않으면
		// age에 null이 들어감
		// null은 int 타입에 들어갈 수 없으므로 Integer 사용
		log.info("username={}, age={}", username, age);

		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(
		@RequestParam(defaultValue = "guest") String username,
		@RequestParam(defaultValue = "-1") int age) {
		// required가 false여도 int age에 값을 입력하지 않으면
		// age에 null이 들어감
		// null은 int 타입에 들어갈 수 없으므로 Integer 사용
		log.info("username={}, age={}", username, age);

		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
		// required가 false여도 int age에 값을 입력하지 않으면
		// age에 null이 들어감
		// null은 int 타입에 들어갈 수 없으므로 Integer 사용
		log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

		return "ok";
	}

	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@ModelAttribute HelloData helloData) {
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

		return "ok";
	}

	// 어노테이션 생략시
	// String, int, Integer 같은 단순 타입 -> @RequestParam
	// 나머지 -> @ModelAttribute (argument resolver로 지정해둔 타입 외)
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(HelloData helloData) {
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

		return "ok";
	}
}
