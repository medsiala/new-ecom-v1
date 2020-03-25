package org.sid.newecomv1;

import net.bytebuddy.utility.RandomString;
import org.sid.newecomv1.dao.CategoryRepository;
import org.sid.newecomv1.dao.ProductRepository;
import org.sid.newecomv1.entities.Category;
import org.sid.newecomv1.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class NewEcomV1Application implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration configuration ;
    public static void main(String[] args) {
        SpringApplication.run(NewEcomV1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        configuration.exposeIdsFor(Product.class);
        configuration.exposeIdsFor(Category.class);
        categoryRepository.save(new Category(null,"PC",null,null,null));
        categoryRepository.save(new Category(null,"Printers",null,null,null));
        categoryRepository.save(new Category(null,"Smartphone",null,null,null));

        categoryRepository.findAll().forEach(c->{
            for(int i=0 ;i<10 ; i++ ){
                Random rnd = new Random();
                Product p = new Product();
                p.setName(RandomString.make(18));
                p.setCurrentPrice(100 + rnd.nextInt(10000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setPhoto("unknown.png");
                p.setCategory(c);
                productRepository.save(p);

            }});
    }
}
