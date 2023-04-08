package hello.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TrafficController {

	public TrafficController(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// CPU 사용량 초과
	@GetMapping("cpu")
	public String cpu() {
		log.info("cpu");
		long value = 0;
		for (long i = 0; i < 10000000000000000L; i++) {
			value++;
		}
		return "ok value = " + value;
	}

	private List<String> list = new ArrayList<>();

	// JVM 메모리 사용량 초과
	@GetMapping("jvm")
	public String jvm() {
		log.info("jvm");
		for (int i = 0; i < 1000000; i++) {
			list.add("hello jvm!" + i);
		}
		return "ok";
	}

	final DataSource dataSource;

	// 커넥션 풀 고갈
	@GetMapping("jdbc")
	public String jdbc() throws SQLException {
		log.info("jdbc");
		final Connection conn = dataSource.getConnection();
		log.info("connection info={}", conn);
		// conn.close(); // 커넥션을 닫지 않는다.
		return "ok";
	}

	// 에러 로그 출력
	@GetMapping("error-log")
	public String errorLog() throws SQLException {
		log.error("error log");
		return "error";
	}

}
