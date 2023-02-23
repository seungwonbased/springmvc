package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MappingController {

	@RequestMapping("/hello-basic")
	public String helloBasic() {
		log.info("helloBasic");
		return "ok";
	}

	// 여기서부터 Postman 테스트

	@RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
	public String mappingGetV1() {
		log.info("helloBasic");
		return "ok";
	}

	@GetMapping("/mapping-get-v2")
	public String mappingGetV2() {
		log.info("helloBasic");
		return "ok";
	}

	//	@RequestMapping 은 URL 경로를 템플릿화 할 수 있는데,
	//	@PathVariable 을 사용하면 매칭 되는 부분을 편리하게 조회할 수 있다.
	//	@PathVariable 의 이름과 파라미터 이름이 같으면 생략할 수 있다.

	@GetMapping("/mapping/{userId}/orders/{orderId}")
	public String mappingPath(
		@PathVariable String userId,
		@PathVariable Long orderId) {
		log.info("mappingPath userId={}, orderId={}", userId, orderId);
		return "ok";
	}
}
