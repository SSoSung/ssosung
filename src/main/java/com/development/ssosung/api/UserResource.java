package com.development.ssosung.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.development.ssosung.domain.User;
import com.development.ssosung.dto.UserDto;
import com.development.ssosung.global.common.SsoSungApiResponse;
import com.development.ssosung.global.common.SsoSungStatus;
import com.development.ssosung.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"사용자 CRUD 컨트롤러"}, description = "사용자 CRUD")
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserResource {

    //    나만의 규칙
    //    C -> void
    //    R -> return
    //    U -> void
    //    D -> void

    private final UserService userService;

    @ApiOperation(value="사용자 정보 생성", notes="회원가입을 한다.")
    @PostMapping
    public ResponseEntity<SsoSungApiResponse> createUser(@Valid @RequestBody UserDto.UserCreateRequest request){
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SsoSungApiResponse(SsoSungStatus.CREATED, "회원가입완료"));
    }

    @ApiOperation(value="사용자 정보 조회", notes="사용자를 조회한다.")
    @GetMapping
    public ResponseEntity<SsoSungApiResponse> readUser(@Valid @RequestBody UserDto.UserReadRequest request){
        return userService.readUser(request);
    }

    @ApiOperation(value="사용자 정보 수정", notes="사용자의 이름, 휴대전화 정보를 수정한다.")
    @PatchMapping
    public ResponseEntity<SsoSungApiResponse> updateUser(@Valid @RequestBody UserDto.UserUpdateRequest request){
        userService.updateUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(new SsoSungApiResponse(SsoSungStatus.OK, "회원수정완료"));
    }

    @ApiOperation(value="사용자 정보 삭제", notes="사용자를 삭제한다.")
    @DeleteMapping
    public ResponseEntity<SsoSungApiResponse> deleteUser(@Valid @RequestBody UserDto.UserDeleteRequest request){
        userService.deleteUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(new SsoSungApiResponse(SsoSungStatus.OK, "회원삭제완료"));
    }

    @ApiOperation(value="Access Token 재발급", notes="Access Token이 만료되면 재발급한다.(Refresh Token이 만료되면 다시 로그인 시키는 구조)")
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String userId = decodedJWT.getSubject();
                User user = userService.getUser(userId);
                String access_token = JWT.create()
                        .withSubject(user.getUserId())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getUserRole())
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
