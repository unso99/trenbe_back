package com.myShop.jwt;

import com.myShop.member.MemberDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//요청할때마다 한번 걸치는 필터(스프링 프레임워크 내에서 동작하는 필터)
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    @Value("${jwt.name}")
    private String jwtName;
    private final JwtUtil jwtUtil;
    private final MemberDetailService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //클라이언트가 요청 헤더에 담은 정보를 얻어낸다
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;

        //인증 Header가 존재하고 해당 문자열이 Bearer로 시작하는지 확인
        if (authHeader != null && authHeader.startsWith("Bearer+")) {
            token = authHeader.substring(7);
            email = jwtUtil.extractMemberName(token);
        }

        //이메일이 존재하고 spring security 에서 아직 인증을 받지 않는 상태라면
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //DB에서 UserDetail객체를 얻어내서
            UserDetails userDetails = service.loadUserByUsername(email);
            //토큰이 유효한지 체크
            boolean isValid = jwtUtil.validateToken(token, userDetails);
            //토큰이 유효하다면
            if (isValid) {
                //자격 증명을 저장
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //1회성 로그인
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
