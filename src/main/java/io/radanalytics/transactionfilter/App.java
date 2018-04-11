package  radanalytics.transactionfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class App {
    @PostConstruct
    public void init() {
        Filter filter = new Filter();
        filter.filterData();
        }

        public static void main(String[] args)
        {
            SpringApplication.run(App.class, args);
        }
}
