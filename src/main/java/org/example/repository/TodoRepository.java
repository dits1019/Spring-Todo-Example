package org.example.repository;

import org.example.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 여기서 Repository는
// 실제 데이터를 저장하고 있는 클래스가 아닌
// 데이터를 주고 받는 인터페이스
// 데이터베이스랑 데이터를 주고받기 위한 인터페이스를 정의한 부분

// JpaRepository에는 앞에 데이터베이스 테이블과 연결될 객체,
// 다음은 해당 객체에 Id에 해당하는 필드 타입

@Repository // Repository라고 명시해주기
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
