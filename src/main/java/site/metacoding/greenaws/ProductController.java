package site.metacoding.greenaws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductRepository productRepository;

    // 이렇게 해야 배포용과 개발용 경로를 다르게 할 수 있음
    @Value("${pic.path}") // application.yml에 있는 property 값을 끌어올 수 있음
    private String path;

    // 경로 테스트
    @GetMapping("/image")
    public ResponseEntity<?> image() {
        return new ResponseEntity<>("이미지 저장 경로 : " + path, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> save(Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED); // 201
    }
}