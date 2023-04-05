package github.Jimoou.organizationservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrganizationServiceApplication {
  @Bean
  public ModelMapper modelMap() {
    return new ModelMapper();
  }

  public static void main(String[] args) {
    SpringApplication.run(OrganizationServiceApplication.class, args);
  }
}
