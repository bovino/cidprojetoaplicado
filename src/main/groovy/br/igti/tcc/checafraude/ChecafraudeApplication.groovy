package br.igti.tcc.checafraude

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ChecafraudeApplication {

	static void main(String[] args) {
		SpringApplication.run ChecafraudeApplication, args
	}
}
