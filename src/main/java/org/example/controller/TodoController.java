package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.TodoResponse;
import org.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 서버에 들어온 요청을 받는 부분
// 요청에 따라서 어떤 작업을 처리하고 어떤 응답을 내려줄 지 구현

@CrossOrigin // CURS 라는 이슈를 받기 위해서
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
    private final TodoService todoService;

    // 생성
    @PostMapping
    public ResponseEntity<TodoResponse> create() {
        System.out.println("CREATE");
        return null;
    }

    // 하나만 조회
    // 경로에 id값을 받아오기
    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne() {
        System.out.println("READ ONE");
        return null;
    }

    // 전체 조히
    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll() {
        System.out.println("READ ALL");
        return null;
    }

    // 수정
    // (@PutMapping도 수정)
    // PUT은 모든 것을 업데이트, PATCH는 일부를 업데이트
    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update() {
        System.out.println("UPDATE");
        return null;
    }

    // 하나만 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne() {
        System.out.println("DELETE");
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        System.out.println("DELETE ALL");
        return null;
    }

}
