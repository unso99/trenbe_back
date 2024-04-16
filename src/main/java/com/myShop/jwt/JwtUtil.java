package com.myShop.jwt;

import com.myShop.member.MemberDao;
import com.myShop.member.MemberDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtUtil {

    //토큰 발급시 서명할 key
    @Value("${jwt.secret}")
    private String secret;
    //토큰 만료
    @Value("${jwt.expiration}")
    private long expiration;

    private final MemberDao dao;

    public String extractMemberName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //토큰을 만들어서 리턴해주는 메소드
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<String, Object>();
        MemberDto dto = dao.getMember(email);
        claims.put("email", email);
        claims.put("password", dto.getPassword());
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        //JwtBuilder 객체를 이용해서 토큰을 만든다.
        return Jwts.builder()
                .setClaims(claims)  //토큰에 담을 추가 정보
                .setSubject(subject) //토큰의 주제(사용자명 or 사용자의 id or 기관명 or 기기명)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration))//토큰 무효화 되는 시간
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact(); // HS256 알고리즘으로 서명해서 토큰얻어내기
    }

    //토큰 유효성 여부를 리턴하는 메소드
    public Boolean validateToken(String token, UserDetails userDetails) {
        //토큰으로 부터 userName 를 얻어내서
        final String username = extractMemberName(token);
        //DB 에 저장된 userName 이고 토큰 유효기간이 만료가 안되었는지 확인해서 유효성 여부를 리턴한다.
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
