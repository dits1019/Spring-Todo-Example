package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
// 파라미터가 없는 기본 생성자 생성
// TodoEntity todoEntity = new TodoEntity();
@NoArgsConstructor
// 모든 필드 값을 파라미터로 받는 생성자 생성
// TodoEntity todoEntity = new TodoEntity(?, ?, ?, ?);
@AllArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // order 키워드가 이미 h2database에서 예약어로 사용학 있기 때문에
    // 별도로 컬럼명 지정
    @Column(name = "todoOrder", nullable = false)
    private Long order;

    @Column(nullable = false)
    private Boolean completed;
}
