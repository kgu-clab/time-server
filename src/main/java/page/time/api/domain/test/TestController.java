package page.time.api.domain.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import page.time.api.global.common.ApiResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "Test", description = "테스트")
public class TestController {

    @Operation(summary = "테스트", description = "테스트")
    @PostMapping("")
    public ApiResponse<?> createAccuse(
    ) {
        return ApiResponse.success("Test success");
    }

}

