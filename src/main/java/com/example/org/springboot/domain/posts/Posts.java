package com.example.org.springboot.domain.posts;

import com.example.org.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity  // 테이블과 링크될 클래스임을 나타내는 어노테이션, setter 메소드를 절대 만들지 않는다. 대신 해당값 변경이 필요할 때 메소드를 추가한다.
public class Posts extends BaseTimeEntity {

    @Id // 테이블의 pk를 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // pk 생성규칙 어노테이션.
    private Long id;

    @Column(length = 500, nullable = false) // 테이블 컬럼 나타내는 어노테이션.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update (String title, String content){
        this.title = title;
        this.content = content;
    }

}
